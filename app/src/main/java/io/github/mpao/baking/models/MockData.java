package io.github.mpao.baking.models;

import java.util.ArrayList;
import java.util.List;
import io.github.mpao.baking.entities.Recipe;

public class MockData {

    public List<Recipe> data = new ArrayList<>();

    public MockData() {

        data.add(new Recipe(0, "Prima ricetta"));
        data.add(new Recipe(1, "Nome ricetta"));
        data.add(new Recipe(2, "Nome ricetta"));
        data.add(new Recipe(3, "Nome ricetta"));
        data.add(new Recipe(4, "Nome ricetta"));
        data.add(new Recipe(5, "Nome ricetta"));
        data.add(new Recipe(6, "Nome ricetta"));
        data.add(new Recipe(7, "Nome ricetta"));
        data.add(new Recipe(8, "Nome ricetta"));
        data.add(new Recipe(9, "Ultima ricetta"));

    }

}
