package soa.web;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SearchController {

	@Autowired
	  private ProducerTemplate producerTemplate;

	@RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping(value="/search")
    @ResponseBody
    public Object search(@RequestParam("q") String q) {
        //Set of operations for taking the word and the max number.
				int i= q.indexof("max:");
				String cadena = q.substring(i);
				String[] subcadenas = cadena.split(":");
				cadena = subcadenas[1];
				Integer cuenta = Integer.parseInt(cadena);
				q = q.substring(0,i);
				//Now we work with it for the funcionality
				Map<String,Object> headers = new HashMap<String,Object>();
			 headers.put("CamelTwitterKeywords",q);
			 headers.put("CamelTwitterCount",count);
			 return producerTemplate.requestBodyAndHeaders("direct:search", "", headers);
    }
}
