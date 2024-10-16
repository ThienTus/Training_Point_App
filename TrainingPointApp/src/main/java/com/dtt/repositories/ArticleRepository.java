/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dtt.repositories;

import com.dtt.pojo.Article;
import java.util.List;

/**
 *
 * @author ThienTu
 */

public interface ArticleRepository {
    List<Article> getArticles();
    Article getArticleById(int id);
    void addOrUpdate(Article c);
    void delete(Article c);
    Article findByName(String name);
}
