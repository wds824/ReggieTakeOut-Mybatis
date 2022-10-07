package com.wds.controller;

import com.wds.common.BaseContext;
import com.wds.common.JsonResult;
import com.wds.entity.AddressBook;
import com.wds.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author WDs , wds8.24@outlook.com
 * @version 1.0
 * @since 2022-10-01 17:07
 */
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService service;

    @GetMapping("/list")
    public JsonResult getList() {
        List<AddressBook> list = service.getList(BaseContext.getUserId());
        return JsonResult.ok(list);
    }

    @PutMapping("/default")
    public JsonResult setDefault(@RequestBody AddressBook addressBook){
        service.setDefault(addressBook.getId());
        return JsonResult.ok();
    }

    @GetMapping("/{id}")
    public JsonResult get(@PathVariable Long id){
        AddressBook book = service.getById(id);
        return JsonResult.ok(book);
    }

    @DeleteMapping()
    public JsonResult delete(Long ids){
        service.remove(ids);
        return JsonResult.ok();
    }

    @PutMapping()
    public JsonResult update(@RequestBody AddressBook book){
        service.update(book);
        return JsonResult.ok();
    }
    @PostMapping()
    public JsonResult save(@RequestBody AddressBook book){
        service.save(book);
        return JsonResult.ok();
    }

    @GetMapping("/default")
    public JsonResult getDefault(){
        AddressBook addressBook = service.getDefault();
        if (addressBook == null) {
            return null;
        }
        return JsonResult.ok(addressBook);
    }
}
