package controller;

import entityVO.DocumentVO;
import org.apache.commons.collections.map.HashedMap;
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

    @RequestMapping("/upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("upload");
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile multipartFile = multipartRequest.getFile("file");
        File xmlFile = (File) multipartFile;
        try {
            manageService.uploadDocument(xmlFile);
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

    }
}