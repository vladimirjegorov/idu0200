package ee.ttu.idu0200.validator;

import ee.ttu.idu0200.form.ArticleForm;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.time.DateUtils.parseDate;

public class ArticleFormValidator {

  public Map<String, String> validate(ArticleForm articleForm) {
    Map<String, String> bindingResult = new HashMap<>();

    validatePublishedDate(articleForm.getPublishedDate(), bindingResult);
    validateTitle(articleForm.getTitle(), bindingResult);
    validateContent(articleForm.getContent(), bindingResult);

    return bindingResult;
  }

  void validatePublishedDate(String publishedDate, Map<String, String> bindingResult) {
    if (isBlank(publishedDate)) {
      bindingResult.put("article.publishedDate", "article.publishedDate.empty");
    } else {
      try {
        parseDate(publishedDate, "dd-MM-yyyy");
      } catch (ParseException e) {
        bindingResult.put("article.publishedDate", "article.publishedDate.invalidFormat");
      }
    }
  }

  void validateTitle(String title, Map<String, String> bindingResult) {
    if (isEmpty(title)) {
      bindingResult.put("article.title", "article.title.empty");
    } else {
      if (title.length() > 100) {
        bindingResult.put("article.title", "article.title.tooLong");
      }
    }
  }

  void validateContent(String content, Map<String, String> bindingResult) {
    if (isEmpty(content)) {
      bindingResult.put("article.content", "article.content.empty");
    } else {
      if (content.length() > 500) {
        bindingResult.put("article.content", "article.content.tooLong");
      }
    }
  }
}
