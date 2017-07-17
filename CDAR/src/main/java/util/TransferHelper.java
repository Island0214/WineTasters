package util;

import entityPO.DocumentPO;
import entityVO.DocumentVO;

import java.util.ArrayList;
import java.util.List;

/**
 * vo与po转换类
 * Created by Mark.W on 2017/7/17.
 */
public class TransferHelper {

    public static List<DocumentVO> transToDocumentVOList(List<DocumentPO> documentPOs) {
        if(documentPOs == null || documentPOs.size() == 0) {
            return null;
        }
        List<DocumentVO> result = new ArrayList<>();
        for (DocumentPO temp : documentPOs) {
            result.add(transToDocumentVO(temp));
        }
        return result;
    }

    private static DocumentVO transToDocumentVO(DocumentPO documentPO) {
        if(documentPO == null) {
            return null;
        }

        return new DocumentVO(documentPO.getId(), documentPO.getCaseNumber(), documentPO.getCaseNumber(), documentPO.getCourt(),
                documentPO.getEndDate(), documentPO.getJudgeReason(), documentPO.getProperty(), documentPO.getEvidence(),
                documentPO.getProcess(), documentPO.getReason(), documentPO.getLitigant(), documentPO.getOriginDocument());
    }



}
