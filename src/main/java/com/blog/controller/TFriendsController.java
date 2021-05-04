package com.blog.controller;


import com.blog.pojo.TFriends;
import com.blog.service.TFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author MR
 * @since 2021-04-20
 */
@Controller
public class TFriendsController {

    @Autowired
    private TFriendsService friendsService;

    @GetMapping("/admin/friendsList")
    public String friendsList(ModelMap map,
                              @RequestParam(value = "pn", required = false, defaultValue = "1") Long pn,
                              @RequestParam(value = "size", required = false, defaultValue = "10") Long size,
                              @RequestParam(value = "name", required = false) String name,
                              @RequestParam(value = "type", required = false) Boolean type) {
        HashMap<String, Object> hashMap = friendsService.friendsList(pn, size, name, type);
        map.put("name", name);
        map.put("type", type);
        map.put("pn", pn);
        map.put("size", size);
        map.put("friendsList", hashMap.get("friendsList"));
        map.put("pages", hashMap.get("pages"));
        return "admin/friendsList";
    }

    @GetMapping("/friends")
    public String friends(ModelMap map) {
        map.put("friendsList1", friendsService.allFriend(true));
        map.put("friendsList2", friendsService.allFriend(false));
        return "friends";
    }

    @PostMapping("/admin/deleteFriend")
    @ResponseBody
    public HashMap<String, Object> deleteFriend(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        int re = friendsService.deleteFriend(id);
        if (re != 0) {
            hashMap.put("info", 1);
            return hashMap;
        }
        hashMap.put("info", 0);
        return hashMap;
    }

    @PostMapping("/admin/addFriend")
    @ResponseBody
    public HashMap<String, Object> addFriend(TFriends friend) {
        HashMap<String, Object> hashMap = new HashMap<>();
        int re = friendsService.addFriend(friend);
        if (re != 0) {
            hashMap.put("info", 1);
            return hashMap;
        }
        hashMap.put("info", 0);
        return hashMap;
    }

    @PostMapping("/admin/updateFriend")
    @ResponseBody
    public HashMap<String, Object> updateFriend(TFriends friend) {
        HashMap<String, Object> hashMap = new HashMap<>();
        int re = friendsService.updateFriend(friend);
        if (re != 0) {
            hashMap.put("info", 1);
            return hashMap;
        }
        hashMap.put("info", 0);
        return hashMap;
    }
}

