package com.lrm.util;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.*;

/**
 * Created by Daniel on 2022/10/22.
 */
public class MarkdownUtils {

    /**
     * markdown格式轉換成HTML格式
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown) {
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    /**
     * 增加擴展[標題錨點，表格生成]
     * Markdown轉換成HTML
     * @param markdown
     * @return
     */
    public static String markdownToHtmlExtensions(String markdown) {
        //h標題稱成id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //轉換table的HTML
        List<Extension> tableExtension = Arrays.asList(TablesExtension.create());
        Parser parser = Parser.builder()
                .extensions(tableExtension)
                .build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder()
                .extensions(headingAnchorExtensions)
                .extensions(tableExtension)
                .attributeProviderFactory(new AttributeProviderFactory() {
                    public AttributeProvider create(AttributeProviderContext context) {
                        return new CustomAttributeProvider();
                    }
                })
                .build();
        return renderer.render(document);
    }

    /**
     * 處理標籤的屬性
     */
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            //改變a標籤的target屬性為_blank
            if (node instanceof Link) {
                attributes.put("target", "_blank");
            }
            if (node instanceof TableBlock) {
                attributes.put("class", "ui celled table");
            }
        }
    }


    public static void main(String[] args) {
        String table = "| hello | hi   | 哈哈哈   |\n" +
                "| ----- | ---- | ----- |\n" +
                "| 斯維爾多  | 士大夫  | 啊    |\n" +
                "| 阿什頓發  | 非固定杆 | 撒阿什頓發 |\n" +
                "\n";
        String a = "[imCoding 愛編程](https://caerus0321.com/)";
        System.out.println(markdownToHtmlExtensions(a));
    }
}
