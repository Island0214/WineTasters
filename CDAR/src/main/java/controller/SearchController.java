package controller;

import entityVO.DocumentVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.AbstractMapDecorator;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;
import service.AnalysisService;
import service.ManageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

//import static sun.plugin2.util.PojoUtil.toJson;

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
    public Map<String, Object> searchFile(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        String input = request.getParameter("data");
        System.out.println(input);

        List<String> keys = Arrays.asList((input.split(" ")));

        List<DocumentVO> documentVOS = analysisService.recommendByKeywords("",keys);
        if(documentVOS != null){
            map.put("input", input);
            map.put("success", "true");
            map.put("content", documentVOS);
            System.out.println(documentVOS.size());
        }else{
            map.put("success", "false");
            map.put("searchInfo", "无对应结果");
        }

//        ModelAndView modelAndView = new ModelAndView()
        return map;
    }

    @RequestMapping(value = "findCase", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findCaseByID(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        String id = request.getParameter("id");
        System.out.println("caseid:" + id);


        DocumentVO documentVOS = manageService.getDocumentByCaseNumber(id);
        if(documentVOS != null){
            map.put("success", "true");
            map.put("content", documentVOS);
        }else{
            map.put("success", "false");
            map.put("searchInfo", "无对应结果");
        }

//        ModelAndView modelAndView = new ModelAndView()
        return map;
    }

    @RequestMapping(value = "getSimilarCases", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSimilarCasesByKeywords(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        String keywordsStr = request.getParameter("keywords");
        String caseNumber = request.getParameter("caseNumber");
        List<String> keywordList = Arrays.asList(keywordsStr.split("/"));
//        for (int i = 0; i < keywordList.size(); i++) {
//            System.out.println("list:" + keywordList.get(i));
//        }

        List<DocumentVO> recomendDocumentVOS = analysisService.recommendByKeywords(caseNumber,keywordList);
        if(recomendDocumentVOS != null){
            for (int i = 0; i < recomendDocumentVOS.size(); i++) {
                System.out.println("vo:" + recomendDocumentVOS.get(i).caseNumber);
            }
            map.put("success", "true");
            map.put("content", recomendDocumentVOS);
        }else{
            map.put("success", "false");
            map.put("searchInfo", "无对应结果");
        }

//        ModelAndView modelAndView = new ModelAndView()
        return map;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashedMap();
        System.out.println("upload");

        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile multipartFile = multipartRequest.getFile("file");
        CommonsMultipartFile cf= (CommonsMultipartFile)multipartFile; //这个myfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
        File file = fi.getStoreLocation();
        try {
            String up = manageService.uploadDocument(file);
            System.out.println(up);
            if(up != null) {
                map.put("success", "true");
                map.put("caseID", up);
            }else{
                map.put("success", "false");
            }
        } catch (DocumentException e) {
            System.out.println("分析XML文件失败");
        }
        try {
            InputStream inputStream=multipartFile.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String tempString;
            if((tempString = reader.readLine()) != null)
                System.out.println(tempString);

//// 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//                System.out.println(tempString);
////                fileList.add(tempString);
//            }
        } catch (IOException e2) {
            System.out.println("读取XML文件失败");
        }
        return map;
    }

    @RequestMapping(value = "getPageSize", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPageSize() {
        Map<String, Object> map = new HashedMap();
        int pageSize = manageService.getPageNumberByCategory("刑事案件", 10);
        map.put("success", "true");
        map.put("pageSize", pageSize);
        return map;
    }

    @RequestMapping(value = "getPageSizeOfSearchResult", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPageSizeOfSearchResult(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        String input = request.getParameter("input");
        int pageSize = manageService.getPageNumberByRex(input, 10);
        System.out.println(input);
        System.out.println(pageSize);
        map.put("success", "true");
        map.put("pageSize", pageSize);
        return map;
    }

    @RequestMapping(value = "getSearchContent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getSearchContent(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        String input = request.getParameter("input");
        int page = Integer.parseInt(request.getParameter("page"));
        System.out.println(input);
        System.out.println(page);
        List<DocumentVO> documentVOS = manageService.getDocumentsByRex(input, page,10);

        map.put("success", "true");
        map.put("content", documentVOS);
        return map;
    }

    @RequestMapping(value = "getPageContent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPageContent(HttpServletRequest request, HttpServletResponse response) {
        JSONArray jsonArray = new JSONArray();

        Map<String, Object> map = new HashedMap();
        int page = Integer.parseInt(request.getParameter("page"));
        List<DocumentVO> documentVOS = manageService.getDocumentsByCategory("刑事案件", page,10);

        map.put("success", "true");
        map.put("content", documentVOS);
        return map;
    }

    @RequestMapping(value = "getReasonType", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getReasonType() {
        Map<String, Object> map = new HashedMap();
        List<String> reasons = manageService.getAllReason();
        map.put("success", "true");
        map.put("types", reasons);
        return map;
    }

    @RequestMapping(value = "getTypeSize", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTypeSize(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        String type = request.getParameter("type");
        System.out.println(type);
        int pageSize = manageService.getPageNumberByReason(type, 10);
        map.put("success", "true");
        map.put("pageSize", pageSize);
        System.out.println(pageSize);
        return map;
    }

    @RequestMapping(value = "getTypeContent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getTypeContent(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashedMap();
        String reason = request.getParameter("reason");
        int page = Integer.parseInt(request.getParameter("page"));
        System.out.println(reason);
        System.out.println(page);
        List<DocumentVO> documentVOS = manageService.getDocumentsByReason(reason, page,10);

        map.put("success", "true");
        map.put("content", documentVOS);
        return map;
    }
}