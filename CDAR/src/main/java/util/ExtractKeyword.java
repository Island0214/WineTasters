package util;

import org.lionsoul.jcseg.extractor.impl.TextRankKeywordsExtractor;
import org.lionsoul.jcseg.tokenizer.core.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据文本提取关键字
 * Created by Mark.W on 2017/7/16.
 */
public class ExtractKeyword {

    private static String NOT_INCLUDE = "被告原告上述没有出生汉族本院身份证判决";

    public String extractKeyword(String str) {

        if(str == null) {
            return null;
        }

        JcsegTaskConfig tokenizerConfig = new JcsegTaskConfig(false);
        ADictionary dic = DictionaryFactory.createSingletonDictionary(tokenizerConfig);
        ISegment tokenizerSeg = null;
        try {
            tokenizerSeg = SegmentFactory.createJcseg(
                    JcsegTaskConfig.COMPLEX_MODE,
                    tokenizerConfig, dic);
        } catch (JcsegException e) {
            e.printStackTrace();
        }
        //create and initialize the extractor
        TextRankKeywordsExtractor keywordsExtractor = new TextRankKeywordsExtractor(tokenizerSeg);
        keywordsExtractor.setKeywordsNum(15);

        List<String> temp = null;
        List<String> result = new ArrayList<>();

        try {
            temp = keywordsExtractor.getKeywordsFromString(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("[0-9]+");

        if (temp != null) {
            for (String s : temp) {
                if (!NOT_INCLUDE.contains(s) && !pattern.matcher(str).matches()) {
                   result.add(s);
                }
            }
        }

        if (result.size() == 0) {
            return null;
        }

        return join(result);
    }

    private String join(List<String> word){
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for(String s:word) {
            if (first) {
                result.append(s);
                first = false;
            }  else {
                result.append("/");
                result.append(s);
            }
        }
        return result.toString();
    }
}
