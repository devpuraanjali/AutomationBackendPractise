import com.chegg.api.BlogService;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class BlogServiceTest {
    private BlogService blogService = new BlogService();

    @Test
    public void validateBlogPost() throws IOException {
        String json = "{\"name\": \"Anjali\"}";
        HttpResponse postResponse = blogService.post(json);

        // 201 - validation for creating a blog post via POST
        int statusCodeCreate = postResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCodeCreate,201);
        String locationHeader = postResponse.getFirstHeader("Location").getValue();
        String[] locationHeaderArray = locationHeader.split("/");
        String blogId = locationHeaderArray[locationHeaderArray.length - 1];

        // 200 - validate the blog post is created via GET
        HttpResponse getResponse = blogService.get(blogId);
        int statusCodeGet = getResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCodeGet,200);
        System.out.println(IOUtils.toString(getResponse.getEntity().getContent(), "UTF-8"));

        //200 - validate the blog post is updated via PUT
        String putContent = "{\"name\": \"Abhi\"}";
        HttpResponse putResponse = blogService.put(blogId, putContent);
        int statusCodePut = getResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCodePut,200);

       // 401 - validate the blog post is deleted via
        HttpResponse httpDelete = blogService.delete("blogId");
        int statusCodeDelete = httpDelete.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCodeDelete,404);
    }

}
