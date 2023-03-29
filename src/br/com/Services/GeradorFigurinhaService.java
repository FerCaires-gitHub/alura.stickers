package br.com.Services;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.MessageFormat;
import javax.imageio.ImageIO;

public class GeradorFigurinhaService {

    
    public void create(InputStream input, String texto, String nomeArquivo) throws IOException
    {
        //leitura da imagem
        var imagem = ImageIO.read(input);
        
        // cria nova imagem em memória com transparência e tamanho novo
        var largura = imagem.getWidth();
        var altura = imagem.getHeight() + 200;

        var novaImagem  = new BufferedImage(largura,altura, BufferedImage.TRANSLUCENT);
        var graphics = (Graphics2D)novaImagem.getGraphics();

        //Configurar a fonte
        var font = new Font("Comic Sans MS",Font.BOLD,72);
        var renderContext = graphics.getFontRenderContext();
        //
        var messageWidth = (int) font.getStringBounds(texto,renderContext).getWidth();
        

        graphics.setFont(font);
        graphics.setColor(Color.GREEN);
        graphics.drawString(texto.toUpperCase(), (largura-messageWidth)/2, altura-100);
        graphics.drawImage(imagem, 0, 0, null);

        nomeArquivo = nomeArquivo.replace(":","");
        ImageIO.write(novaImagem, "png", new File(String.format("src/br/com/resource/%s.png",nomeArquivo)));

    }
}
