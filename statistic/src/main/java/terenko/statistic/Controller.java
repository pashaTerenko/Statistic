package terenko.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import terenko.statistic.API.API;
import terenko.statistic.DTO.PageCountDTO;
import terenko.statistic.DTO.PostDTO;
import terenko.statistic.DTO.UserDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    API api;
    final int  PAGE=5;
    @PostMapping("post")
    public ResponseEntity  pushPost(@RequestParam String slug){
        try {

            api.pushPost(slug);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("comment")
    public List<UserDTO> getComment(@RequestParam int page){
        List<UserDTO> cm=api.getComentatorsList();
        if(cm==null ||cm.size()==0){return null;}
        List<UserDTO> rm =new ArrayList<>();
        page=page>getPageCount(cm)||page<1?1:page;
        for (int i=page*PAGE-PAGE;i<(page*PAGE>cm.size()?cm.size():PAGE*page);i++){
            rm.add(cm.get(i));
        }
        return cm;

    }
    @GetMapping("liker")
    public List<UserDTO> getLike(@RequestParam int page){
        List<UserDTO> cm=api.getLikersList();
        if(cm==null ||cm.size()==0){return null;}
        List<UserDTO> rm =new ArrayList<>();
        page=page>getPageCount(cm)||page<1?1:page;
        for (int i=page*PAGE-PAGE;i<(page*PAGE>cm.size()?cm.size():PAGE*page);i++){
            rm.add(cm.get(i));
        }
        return rm;

    }
    @GetMapping("repost")
    public List<UserDTO> getRepost(@RequestParam int page){
        List<UserDTO> cm=api.getRepostersList();
        if(cm==null ||cm.size()==0){return null;}
        List<UserDTO> rm =new ArrayList<>();
        page=page>getPageCount(cm)||page<1?1:page;
        for (int i=page*PAGE-PAGE;i<(page*PAGE>cm.size()?cm.size():PAGE*page);i++){
            rm.add(cm.get(i));
        }
        return rm;

    }
    @GetMapping("mention")
    public List<UserDTO> getMention(@RequestParam int page){
        List<UserDTO> cm=api.getMentorsList();
        if(cm==null ||cm.size()==0){return null;}
        List<UserDTO> rm =new ArrayList<>();
        page=page>getPageCount(cm)||page<1?1:page;
        for (int i=page*PAGE-PAGE;i<(page*PAGE>cm.size()?cm.size():PAGE*page);i++){
            rm.add(cm.get(i));
        }
        return rm;

    }
    @GetMapping("getpost")
    public PostDTO  getPost(){
        if (api.getPost()!=null)
        return api.getPost();
        else return null;
    }

    private long getPageCount(List<?> list) throws  NullPointerException{
        long totalCount = list.size();
        return (totalCount / PAGE) + ((totalCount % PAGE > 0) ? 1 : 0);
    }
    @GetMapping("count")
    public PageCountDTO getCount(@RequestParam int l){
        try {
            switch (l){
                case 0:return  PageCountDTO.of(api.getLikersList().size(),PAGE);

                case 1:return  PageCountDTO.of(api.getComentatorsList().size(),PAGE);

                case 2:return   PageCountDTO.of(api.getRepostersList().size(),PAGE);

                case 3:return  PageCountDTO.of(api.getMentorsList().size(),PAGE);

                default:return null;
            }
        } catch (NullPointerException e) {
            return null;
        }
    }


}
