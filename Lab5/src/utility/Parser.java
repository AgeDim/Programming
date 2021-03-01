package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Parser {
    /**
     * Parsing csv file
     * @return fileLines
     */
    public ArrayList<String> parseFromFile(File filePath) throws IOException {
        //Загружаем строки из файла

        ArrayList<String> fileLines = new ArrayList<String>();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));
        int c;
        String text = "";
        do {
            c = isr.read();
            if ((char) c == '\n') {
                fileLines.add(text);
                text = "";
                continue;
            }
            text += (char) c;
        } while (c != -1);

        return fileLines;
    }
    public ArrayList<String> getItems(String fileLine) {
        String[] splitedText = fileLine.split(",", 9);
        ArrayList<String> columnList = new ArrayList<String>();
        for (String s : splitedText) {
            //Если колонка начинается на кавычки или заканчиваеться на кавычки
            if (IsColumnPart(s)) {
                String lastText = columnList.get(columnList.size() - 1);
                columnList.set(columnList.size() - 1, lastText + "," + s);
            } else {
                columnList.add(s);
            }
        }

        return columnList;

    }
    /**
     * verification of parsing.
     * @return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
     */
    //Проверка является ли колонка частью предыдущей колонки
    private static boolean IsColumnPart(String text) {
        String trimText = text.trim();
        //Если в тексте одна ковычка и текст на нее заканчиваеться значит это часть предыдущей колонки
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}
