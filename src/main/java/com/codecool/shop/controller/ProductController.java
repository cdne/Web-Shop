package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        CartDao cartDataStore = CartDaoMem.getInstance();
        int cartSize = cartDataStore.getCartNumberOfProducts();
        cartSize = 3;


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (req.getParameter("productCategory") == null) {
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            context.setVariable("cartSize", cartSize);
            engine.process("product/index.html", context, resp.getWriter());

        } else if (req.getParameter("productCategory").equalsIgnoreCase("laptop")) {
            context.setVariable("category", productCategoryDataStore.find(2));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(2)));
            context.setVariable("cartSize", cartSize);
            engine.process("product/index.html", context, resp.getWriter());


        } else if (req.getParameter("productCategory").equalsIgnoreCase("tablet")) {
            context.setVariable("category", productCategoryDataStore.find(1));
            context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
            context.setVariable("cartSize", cartSize);
            engine.process("product/index.html", context, resp.getWriter());

        } else {
            engine.process("product/notFound.html", context, resp.getWriter());
        }


        System.out.println("Category " +req.getParameter("productCategory"));
//        System.out.println(req.getParameterMap());
        if(req.getParameter("addToCart")!=null) {
            System.out.println(req.getParameter("addToCart"));
        }


        // // Alternative setting of the template context
        // Map<String, Object> params = new HashMap<>();
        // params.put("category", productCategoryDataStore.find(1));
        // params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        // context.setVariables(params);

    }

}
