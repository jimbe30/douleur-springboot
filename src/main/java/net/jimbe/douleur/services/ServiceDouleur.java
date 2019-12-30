package net.jimbe.douleur.services;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.jimbe.douleur.beans.OrdonnanceForm;
import net.jimbe.douleur.beans.PrescriptionPreconisee;
import net.jimbe.douleur.dao.PreconisationRepository;
import net.jimbe.douleur.entity.Preconisation;
import net.jimbe.douleur.tools.PDFBuilder;

@Service
public class ServiceDouleur {

	@Autowired
	PreconisationRepository preconisationRepository;

	public List<PrescriptionPreconisee> getPrescriptionsProposees(int idDouleur) {
		List<PrescriptionPreconisee> result = new ArrayList<PrescriptionPreconisee>();
		List<Preconisation> preconisations = preconisationRepository.findByIdDouleur(idDouleur);
		if (preconisations != null) {
			preconisations.forEach(preconisation -> {
				if (result.isEmpty()) {
					PrescriptionPreconisee prescriptionPreconisee = new PrescriptionPreconisee();
					prescriptionPreconisee.addMedicamentPreconise(preconisation);
					result.add(prescriptionPreconisee);
				} else {
					PrescriptionPreconisee lastPrescription = result.get(result.size() - 1);
					Preconisation lastMedicamentPreconise = lastPrescription.getLastMedicamentPreconise();
					if (lastMedicamentPreconise == null
							|| preconisation.getNumOrdonnance() == lastMedicamentPreconise.getNumOrdonnance()) {
						lastPrescription.addMedicamentPreconise(preconisation);
					} else {
						PrescriptionPreconisee prescriptionPreconisee = new PrescriptionPreconisee();
						prescriptionPreconisee.addMedicamentPreconise(preconisation);
						result.add(prescriptionPreconisee);
					}
				}
			});
		}
		return result;
	}

	public String editOrdonnancePDF(String modeleName, String targetName, OrdonnanceForm ordonnance) throws IOException,
			KeyManagementException, NoSuchAlgorithmException, KeyStoreException, URISyntaxException {

		PDFBuilder builder = new PDFBuilder(modeleName);
		PDDocument document = builder.getDocument();
		PDPage page = builder.getFirstPage();

		PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);

		final float fontSize = 12;
		contentStream.setFont(PDType1Font.TIMES_ROMAN, fontSize);

		// médicaments
		contentStream.beginText();
		contentStream.newLineAtOffset(40, 530);

		ordonnance.getPrescription().getMedicaments().forEach(dispensation -> {
			String lignePrescription = dispensation.getProduits().stream()
					.map(produit -> produit.get("designation") + " " + produit.get("dosage"))
					.reduce((actualResult, nextElem) -> actualResult.concat(" + ").concat(nextElem)).get();
			String quantite = dispensation.getQuantite().concat(" ").concat(dispensation.getForme());
			String frequence = dispensation.getFrequence().concat(" fois par jour");
			String duree = "pendant ".concat(dispensation.getDuree()).concat(" jours");
			lignePrescription = lignePrescription.concat(" ; ").concat(quantite).concat(" ; ").concat(frequence)
					.concat(" ; ").concat(duree);

			try {
				contentStream.showText(lignePrescription);
				contentStream.newLineAtOffset(0, -fontSize * 1.5f);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		contentStream.endText();

		// infos bénéf
		PDFont font = PDType1Font.TIMES_BOLD;
		float size = 11;
		contentStream.setFont(font, size);

		String nom = ordonnance.getInfosPatient().getNomPatient() + " - "
				+ ordonnance.getInfosPatient().getPrenomPatient()
				+ ("F".equals(ordonnance.getInfosPatient().getSexe()) ? " - née le " : " - né le ")
				+ new SimpleDateFormat("dd/MM/yyyy").format(ordonnance.getInfosPatient().getDateNaissance());

		String insee = ordonnance.getInfosPatient().getInsee();
		float width = font.getStringWidth(nom) / 1000 * size;
		float xNom = (page.getMediaBox().getWidth() - width) / 2;

		width = font.getStringWidth(insee) / 1000 * size;
		float xInsee = (page.getMediaBox().getWidth() - width) / 2;

		contentStream.beginText();
		contentStream.newLineAtOffset(Math.max(xNom, 170), 650);
		contentStream.showText(nom);
		contentStream.endText();

		contentStream.beginText();
		contentStream.newLineAtOffset(xInsee, 617);
		contentStream.showText(insee);
		contentStream.endText();

		contentStream.close();

		// Saving the document
		File outputFile = new File(targetName);
		outputFile.getParentFile().getCanonicalFile().mkdirs();
		try {
			document.save(outputFile);
		} catch (Exception e) {
			e.printStackTrace();
			outputFile = new File(outputFile.getName());
			document.save(outputFile.getName());
		}
	
		// Closing the document
		document.close();

		return targetName;

	}
	
	public byte[] getOrdonnancePDF(String targetFileName) throws IOException {
		
		InputStream content = new FileInputStream(targetFileName);

		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[1024];
		while ((nRead = content.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
			data = new byte[1024];
		}
		buffer.flush();
		byte[] byteArray = buffer.toByteArray();
		buffer.close();
		
		content.close();
		return byteArray;
	}
	

}
