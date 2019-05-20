package org.chenguoyu.learn.springboot.file.upload;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.chenguoyu.learn.springboot.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传控制器类
 */
@RestController
@RequestMapping("/file/upload")
public class FileUploadController {
    @Value("${file.upload.path}")
    private String path;

    /**
     * 单独文件上传
     *
     * @param file
     * @return
     */
    @ApiOperation("测试单文件上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true, dataType = "__File"),
    })
    @PostMapping("/upload")
    public Map<String, String> upload(@RequestParam MultipartFile file) throws IOException {
        //创建本地文件
        String classpath = ResourceUtils.getURL("classpath:").getPath();
        File localFile = new File(classpath + path, file.getOriginalFilename());
        //把传上来的文件写到本地文件
        file.transferTo(localFile);
        //返回localFile文件路径
        Map<String, String> path = new HashMap<>();
        path.put("path", localFile.getAbsolutePath());
        return path;
    }

    /**
     * 单独文件上传
     * 不使用transferTo方法
     *
     * @param file
     * @return
     */
    @ApiOperation("测试单文件上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true, dataType = "__File"),
    })
    @PostMapping("/singleFileUpload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path filePath = Paths.get(path + file.getOriginalFilename());
        Files.write(filePath, bytes);
        return file.getOriginalFilename();
    }

    /**
     * 测试多文件上传接口
     *
     * @param files
     * @return
     */
    @ApiOperation("测试多文件上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "files", value = "文件流对象,接收数组格式", required = true, dataType = "__File", allowMultiple = true),
    })
    @PostMapping("/uploads")
    public Map<String, String> uploads(@RequestParam MultipartFile[] files) throws IOException {
        StringBuilder sb = new StringBuilder();
        Map<String, String> paths = new HashMap<>();
        for (MultipartFile file : files) {
            //创建本地文件
            File localFile = new File(path, file.getOriginalFilename());
            //把传上来的文件写到本地文件
            file.transferTo(localFile);
            sb.append(localFile.getAbsolutePath()).append(",");
            paths.put(file.getOriginalFilename(), localFile.getAbsolutePath());
        }
        //返回localFile文件路径
        return paths;
    }

    /**
     * @param tmpString
     * @param file
     * @return
     */
    @ApiOperation("测试单文件上传与表单上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tmpString", value = "表单数据", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "文件流对象,接收数组格式", required = true, dataType = "__File"),
    })
    @PostMapping("/uploadWithForm")
    public Map<String, String> uploadWithForm(@RequestParam String tmpString, @RequestParam MultipartFile file) throws IOException {
        //创建本地文件
        File localFile = new File(path, file.getOriginalFilename());
        //把传上来的文件写到本地文件
        file.transferTo(localFile);
        //返回localFile文件路径
        Map<String, String> result = new HashMap<>();
        result.put("tmpString", tmpString);
        result.put("filePath", file.getOriginalFilename());
        return result;
    }

    /**
     * 测试多文件上传+表单接口
     *
     * @param files
     * @return
     */
    @ApiOperation("测试多文件上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tmpString", value = "表单数据", dataType = "String"),
            @ApiImplicitParam(name = "files", value = "文件流对象,接收数组格式", required = true, dataType = "__File", allowMultiple = true),
    })
    @PostMapping("/uploadsWithForm")
    public Map<String, String> uploadsWithForm(@RequestParam String tmpString, @RequestParam MultipartFile[] files) throws IOException {
        StringBuilder sb = new StringBuilder();
        Map<String, String> paths = new HashMap<>();
        paths.put("tmpString", tmpString);
        for (MultipartFile file : files) {
            //创建本地文件
            File localFile = new File(path, file.getOriginalFilename());
            //把传上来的文件写到本地文件
            file.transferTo(localFile);
            sb.append(localFile.getAbsolutePath()).append(",");
            paths.put(file.getOriginalFilename(), localFile.getAbsolutePath());
        }
        //返回localFile文件路径
        return paths;
    }


    /**
     * 测试多文件上传+json接口
     *
     * @param files
     * @return
     */
    @ApiOperation("测试多文件上传接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsonMap", value = "json数据", dataType = "Map"),
            @ApiImplicitParam(name = "files", value = "文件流对象,接收数组格式", required = true, dataType = "__File"),
    })
    @PostMapping(value = "/uploadsWithJson")
    public Map<String, String> uploadsWithJson(@RequestPart("files") MultipartFile[] files, @RequestPart("jsonMap") Map<String, Object> jsonMap) throws IOException {
        StringBuilder sb = new StringBuilder();
        Map<String, String> paths = new HashMap<>();
        for (MultipartFile file : files) {
            //创建本地文件
            File localFile = new File(path, file.getOriginalFilename());
            //把传上来的文件写到本地文件
            file.transferTo(localFile);
            sb.append(localFile.getAbsolutePath()).append(",");
            paths.put(file.getOriginalFilename(), localFile.getAbsolutePath());
        }
        paths.put("jsonMap", JsonUtils.obj2json(jsonMap));
        //返回localFile文件路径
        return paths;
    }
}
