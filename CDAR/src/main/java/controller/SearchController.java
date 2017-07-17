package controller;

import entityVO.DocumentVO;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AnalysisService;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by island on 2017/7/16.
 */
@Controller
@RequestMapping("/manageAction")
public class SearchController {
    @Autowired
    private ManageService manageService;

    @Autowired
    private AnalysisService analysisService;

    @RequestMapping(value = "searchCase", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> searchFile(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashedMap();
        String input = request.getParameter("data");
        System.out.println(input);

        List<String> keys = Arrays.asList((input.split(" ")));

        List<DocumentVO> documentVOS = analysisService.recommendByKeywords(keys);
        return map;
    }
}
