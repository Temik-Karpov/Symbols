package ru.karpov.Test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {

    @GetMapping
    public String getMainPage()
    {
        return "main";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("str") final String string,
                            final Model model)
    {

        Map<String, Integer> values = calculateSymbols(string);
        model.addAttribute("map", sortSymbols(values));
        return "result";
    }

    private Map<String, Integer> sortSymbols(Map<String, Integer> values)
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(values.entrySet());
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );

        Map<String, Integer> result = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : list)
        {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    private Map<String, Integer> calculateSymbols(String str)
    {
        Map<String, Integer> values = new HashMap<>();
        String[] mas = str.split("");
        for(String symbol : mas)
        {
            values.put(symbol, 0);
        }
        for(String symbol : mas)
        {
            values.put(symbol, values.get(symbol) + 1);
        }
        return values;
    }
}
