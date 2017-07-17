package util;

import org.lionsoul.jcseg.extractor.impl.TextRankKeywordsExtractor;
import org.lionsoul.jcseg.tokenizer.core.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据文本提取关键字
 * Created by Mark.W on 2017/7/16.
 */
public class ExtractKeyword {

    public List<String> extractKeyword(String str) {

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

        if (temp != null) {
            for (String s : temp) {
                if (!(s.equals("被告") || s.equals("原告") || s.equals("上述") || s.equals("没有"))) {
                   result.add(s);
                }
            }
        }

        if (result.size() == 0) {
            return null;
        }

        return result;
    }
}
