package com.example.anthony.androidca;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class BookModel extends HashMap<String,Object> {


    final static String baseURL = "http://172.17.249.19/BookStore/Endpoint/IBookService.svc/";

    public BookModel(Object ISBN,Object title, Object authorName, Object categoryName, Object price,Object discountedPrice,Object stockLevel,Object synopsis) {

        put("ISBN", ISBN);
        put("title", title);
        put("authorName", authorName);
        put("categoryName", categoryName);
        put("price",price.toString());
        put("discountedPrice",discountedPrice.toString());
        put("stockLevel",stockLevel.toString());
        put("synopsis",synopsis);
    }


    public static List<String> list() {
        List<String> list = new ArrayList<String>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "Books");
        try {
            for (int i =0; i<a.length(); i++){
                JSONObject b = a.getJSONObject(i);
                list.add(b.get("ISBN").toString());
            }

        } catch (Exception e) {
            Log.e("BookModel.list()", "JSONArray error");
        }
        return(list);
    }

    public static BookModel getBook(String ISBN) {
        JSONObject b = JSONParser.getJSONFromUrl(baseURL  + "Books/" + ISBN );
        try {
            return new BookModel(b.get("ISBN"),b.get("title"),b.get("authorName"),b.get("categoryName"),b.get("price"),b.get("discountedPrice"),b.get("stockLevel"),b.get("synopsis"));
        } catch (Exception e) {
            Log.e("BookModel.getBook()", "JSONArray error");
        }
        return(null);
    }

    public static List<String> searchBookByTitle(final String searchCriteria){
        List<String> allBooksISBN = list();

        final List<String> searchResult = new ArrayList<String>();
        final List<String> searchWords = getIndividualSearchWords(searchCriteria);

        for (final String isbn: allBooksISBN) {

            BookModel book = getBook(isbn);

            if(BookModel.hasMatchingString(book, searchCriteria)){
                searchResult.add(book.get("ISBN").toString());
            }
            else{
                if(BookModel.hasMatchingWords(book,searchWords)&& !resultsContain(searchResult,book))
                    searchResult.add(book.get("ISBN").toString());
            }

        }
        return searchResult;
    }

    private static List<String> getIndividualSearchWords(String searchCriteria) {
        List<String> searchWords = new ArrayList<String>(Arrays.asList(searchCriteria.toLowerCase().split("\\s+|[,.-<>]")));
        List<String> omittedWords = Arrays.asList("the","and","a","of","to");

        Iterator<String> i = searchWords.iterator();
        while(i.hasNext()){
            String s = i.next();
            for (String omittedWord:omittedWords) {
                if(s.equals(omittedWord))
                    i.remove();
            }
        }
        return searchWords;
    }

    private static boolean hasMatchingString(BookModel book, String s) {
        return (book.get("title").toString().toLowerCase()).contains(s.trim().toLowerCase());
    }

    private static boolean hasMatchingWords(BookModel book,List<String> searchWords){
        for(String words : searchWords){
            if(hasMatchingString(book, words) ){
                return true;
            }
        }
        return false;
    }

    public static boolean resultsContain(final List<String> searchResultsWithISBN, final BookModel book){
        for(String ISBN: searchResultsWithISBN){
            BookModel bookInResult = getBook(ISBN);
            if(bookInResult.get("title").equals(book.get("title")))
                return true;
        }
        return false;
    }
}

