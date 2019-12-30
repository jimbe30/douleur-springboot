package net.jimbe.douleur.tools;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import net.jimbe.douleur.beans.OrdonnanceForm;

public class PDFBuilder {

	protected PDDocument document;
	protected URL urlInput;
	protected PDFont defaultFont = PDType1Font.TIMES_ROMAN;

	private int nextNumPage = 0;
	private PDPage currentPage = null;

	public PDFBuilder() throws IOException {
		document = new PDDocument();
		addPage();
	}

	public PDFBuilder(String srcLocation) throws IOException, KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException, URISyntaxException {
		InputStream input = null;

		urlInput = getClass().getResource(srcLocation);
		if (urlInput == null) {
			urlInput = new URL(srcLocation);
		}

		try {
			input = urlInput.openStream();
		} catch (SSLHandshakeException e) {
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, (chain, authType) -> true).build();
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
			HttpClient httpClient = HttpClientBuilder.create().setSSLSocketFactory(csf).build();
			HttpResponse response = httpClient.execute(new HttpGet(urlInput.toURI()));
			input = response.getEntity().getContent();
		}

		document = PDDocument.load(input);
		document.setAllSecurityToBeRemoved(true);
	}

	public PDPage addPage() throws IOException {
		PDRectangle mediaBox = PDRectangle.A4;
		PDPage page = new PDPage(mediaBox);
		document.addPage(page);
		currentPage = page;
		nextNumPage = getNbPages();
		return page;
	}

	public PDPageContentStream getContentStream() throws IOException {
		PDPageContentStream contentStream = new PDPageContentStream(this.document, this.getCurrentPage(true),
				AppendMode.APPEND, true);
		return contentStream;
	}

	public int getNbPages() {
		return document.getPages().getCount();
	}

	public PDPage getLastPage() throws IOException {
		PDPage result = null;
		int nbPages = getNbPages();
		if (nbPages > 0) {
			result = document.getPage(nbPages - 1);
			currentPage = result;
			nextNumPage = getNbPages();
		}
		return result;
	}

	public PDPage getFirstPage() throws IOException {
		PDPage result = null;
		int nbPages = getNbPages();
		if (nbPages > 0) {
			result = document.getPage(0);
			currentPage = result;
			nextNumPage = 1;
		}
		return result;
	}

	public PDPage getNextPage() throws IOException {
		PDPage result = null;
		int nbPages = getNbPages();
		if (nbPages > 0 && nextNumPage < nbPages) {
			result = document.getPage(nextNumPage);
			currentPage = result;
			nextNumPage++;
		}
		return result;
	}

	public PDPageContentStream drawEllipse(float xPos, float yPos, float width, float height, Color backgroundColor,
			Color borderColor, float borderWidth) throws IOException {

		PDPageContentStream contentStream = getContentStream();
		/*
		 * coin supérieur gauche = xPos ; yPos => milieu partie supérieure = xPos +
		 * largeur / 2 ; yPos et milieu partie inférieure = xPos + largeur / 2 ; yPos -
		 * hauteur
		 */
		float x0 = xPos + width / 2, y0 = yPos;
		float x3 = x0, y3 = y0 - height;
		/*
		 * demi ellipse droite 1er point de contrôle : x0 + 4/3 de largeur /2 ; y0 2ème
		 * point de contrôle : x0 + 4/3 de largeur /2 ; y0 - hauteur
		 */
		float x1 = (x0 + 2 * width / 3), y1 = y0;
		float x2 = x1, y2 = y0 - height;
		/*
		 * demi ellipse gauche 1er point de contrôle : x0 - 4/3 de largeur /2 ; y0 2ème
		 * point de contrôle : x0 - 4/3 de largeur /2 ; y0 - hauteur
		 */
		float x1b = (x0 - 2 * width / 3), y1b = y0;
		float x2b = x1b, y2b = y0 - height;

		/*
		 * on trace les 2 demi ellipses
		 */
		contentStream.moveTo(x0, y0);
		contentStream.curveTo(x1, y1, x2, y2, x3, y3);
		contentStream.moveTo(x0, y0);
		contentStream.curveTo(x1b, y1b, x2b, y2b, x3, y3);

		boolean border = false, background = false;

		if (borderColor != null || borderWidth > 0) {
			contentStream.setStrokingColor(borderColor != null ? borderColor : Color.BLACK);
			contentStream.setLineWidth(borderWidth > 0 ? borderWidth : 0.75f);
			border = true;
		}
		if (backgroundColor != null) {
			contentStream.setNonStrokingColor(backgroundColor);
			background = true;
		}
		if (border && background) {
			contentStream.fillAndStroke();
		} else if (border) {
			contentStream.stroke();
		} else if (background) {
			contentStream.fill();
		}
		contentStream.closePath();
		contentStream.setNonStrokingColor(Color.BLACK);

		return contentStream;
	}

	/**
	 * Ellipse sans bordure
	 * 
	 * @param xPos
	 * @param yPos
	 * @param width
	 * @param height
	 * @param backgroundColor
	 * @return
	 * @throws IOException
	 */
	public PDPageContentStream drawEllipse(float xPos, float yPos, float width, float height, Color backgroundColor)
			throws IOException {
		return drawEllipse(xPos, yPos, width, height, backgroundColor, null, 0);
	}

	public PDDocument getDocument() {
		return document;
	}

	public void setDocument(PDDocument document) {
		this.document = document;
	}

	public URL getUrlInput() {
		return urlInput;
	}

	public void setUrlInput(URL urlInput) {
		this.urlInput = urlInput;
	}

	public PDFont getDefaultFont() {
		return defaultFont;
	}

	public void setDefaultFont(PDFont defaultFont) {
		this.defaultFont = defaultFont;
	}

	public PDPage getCurrentPage() throws IOException {
		return getCurrentPage(false);
	}

	public PDPage getCurrentPage(boolean create) throws IOException {
		if (currentPage == null && create) {
			getNextPage();
			if (currentPage == null) {
				addPage();
			}
		}
		return currentPage;
	}

	public static byte[] editOrdonnancePDF(String modeleName, String targetName, OrdonnanceForm ordonnance) throws IOException,
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
		
		ordonnance.getPrescription().getMedicaments().forEach(
			dispensation -> {
				String lignePrescription = dispensation.getProduits().stream()
					.map(produit -> produit.get("designation") + " " + produit.get("dosage"))
					.reduce((actualResult, nextElem) -> actualResult.concat(" + ").concat(nextElem))
					.get();
				String quantite = dispensation.getQuantite().concat(" ").concat(dispensation.getForme());
				String frequence = dispensation.getFrequence().concat(" fois par jour");
				String duree = "pendant ".concat(dispensation.getDuree()).concat(" jours");
				lignePrescription = lignePrescription.concat(" ; ").concat(quantite).concat(" ; ").concat(frequence).concat(" ; ").concat(duree);
				
				try {
					contentStream.showText(lignePrescription);
					contentStream.newLineAtOffset(0, -fontSize * 1.5f);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		);
		contentStream.endText();

		// infos bénéf
		PDFont font = PDType1Font.TIMES_BOLD;
		float size = 11;
		contentStream.setFont(font, size);

		String nom = ordonnance.getInfosPatient().getNomPatient()
				+ " - " + ordonnance.getInfosPatient().getPrenomPatient()
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

		PDFTextStripper pdfStripper = new PDFTextStripper();
		// Retrieving text from PDF document
		String text = pdfStripper.getText(document);

		System.out.println(text);

		// Closing the document
		document.close();

		InputStream content = new FileInputStream(outputFile);

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
