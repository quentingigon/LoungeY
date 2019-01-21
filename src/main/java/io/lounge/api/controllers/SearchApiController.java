package io.lounge.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.lounge.api.interfaces.SearchApi;
import io.lounge.api.utils.DAOUtils;
import io.lounge.models.Post;
import io.lounge.mongo.dao.PostDAO;
import io.lounge.mongo.dao.entities.PostDO;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-16T12:49:56.829Z")

@Controller
public class SearchApiController implements SearchApi {

    private static final Logger log = LoggerFactory.getLogger(SearchApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public SearchApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<Post>> searchPosts(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "searchString", required = true) String searchString) {
		PostDAO postDAO = DAOUtils.getPostDAO();
		ArrayList<Post> posts = new ArrayList<>();

		for (PostDO postDO : postDAO.searchForPosts(searchString)) {
			if (postDO != null)
				posts.add(postDO.toPost());
		}

        return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

}
