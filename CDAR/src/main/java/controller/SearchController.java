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

        List<DocumentVO> documentVOS = analysisService.recommendByKeywords(keys);
        if(documentVOS != null){
            System.out.println(documentVOS.size());
        }else{
            System.out.println("null");
        }
        return map;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashedMap();
        System.out.println("upload");
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        System.out.println("1");

        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
//        System.out.println("2");

        MultipartFile multipartFile = multipartRequest.getFile("file");
//        System.out.println("3");

        CommonsMultipartFile cf= (CommonsMultipartFile)multipartFile; //这个myfile是MultipartFile的
//        System.out.println("4");

        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
//        System.out.println("5");

        File file = fi.getStoreLocation();

        try {
            boolean up = manageService.uploadDocument(file);
            if(up){
                map.put("success", "true");
            }else{
                map.put("success", "false");
            }
        }catch (DocumentException e){
            e.printStackTrace();
        }
        try {
            InputStream inputStream=multipartFile.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String tempString = null;
            if((tempString = reader.readLine()) != null)
                System.out.println(tempString = reader.readLine());

//// 一次读入一行，直到读入null为文件结束
//            while ((tempString = reader.readLine()) != null) {
//                System.out.println(tempString);
////                fileList.add(tempString);
//            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "getPageSize", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getPageSize() {
        Map<String, Object> map = new HashedMap();
        int pageSize = manageService.getPageNumberByCategory("民事案件", 10);
        map.put("success", "true");
        map.put("pageSize", pageSize);
        return map;
    }

    @RequestMapping(value = "getPageContent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getPageContent(HttpServletRequest request, HttpServletResponse response) {
        JSONArray jsonArray = new JSONArray();

        Map<String, Object> map = new HashedMap();
        int page = Integer.parseInt(request.getParameter("page"));
//        System.out.println(page);
        List<DocumentVO> documentVOS = manageService.getDocumentsByCategory("民事案件", page,10);
//        DocumentVO documentVO;
//        for(int i = 0; i < documentVOS.size(); i++){
//            documentVO = documentVOS.get(i);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("id", documentVO.id);
//            jsonObject.put("title", documentVO.title);
//            jsonObject.put("caseNumber", documentVO.caseNumber);
//            jsonObject.put("court", documentVO.court);
//            jsonObject.put("endDate", documentVO.endDate);
//            jsonObject.put("judge_reason", documentVO.judge_reason);
//            jsonObject.put("property", documentVO.property);
//            jsonObject.put("evidence", documentVO.evidence);
//            jsonObject.put("process", documentVO.process);
//            jsonObject.put("reason", documentVO.reason);
//            jsonObject.put("litigant", documentVO.litigant);
//            jsonObject.put("originDocument", documentVO.originDocument);
//            map.put(i+"", jsonObject);
//        }
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