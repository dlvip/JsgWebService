package com.old.time.controller;

import com.old.time.domain.FastMailEnity;
import com.old.time.domain.Result;
import com.old.time.repository.FastMailRepository;
import com.old.time.utils.ResultUtil;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping(value = "jiushiguang/fastMail")
public class FastMailController extends BaseController {

    @Autowired
    private FastMailRepository fastMailRepository;

    @PostMapping(value = "/saveFastMailList")
    public Result savePhoneList() {
        String path = FastMailController.class.getResource("/assets").getPath();
        File jsonFile = null;
        try {
            jsonFile = ResourceUtils.getFile(path + "/fast_mail.json");
            String json = FileUtils.readFileToString(jsonFile, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject fastMailObj = jsonArray.getJSONObject(i);
                FastMailEnity mFastMailBean = new FastMailEnity();
                mFastMailBean.setName(fastMailObj.getString("name"));
                mFastMailBean.setIcon(fastMailObj.getString("icon"));
                mFastMailBean.setUrl(fastMailObj.getString("url"));
                mFastMailBean.setId(fastMailObj.getString("id"));
                if (!fastMailRepository.existsFastMailEnitieById(mFastMailBean.getId())) {
                    fastMailRepository.save(mFastMailBean);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ResultUtil.success();
    }

    @PostMapping(value = "/getFastMailList")
    public Result getFastMailList() {

        return ResultUtil.success(fastMailRepository.findAll());

    }

}
