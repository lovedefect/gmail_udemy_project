package com.appsenseca.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

import java.util.Locale;

/**
 * Created by appken on 15-05-03.
 */
@RunWith(Categories.class)
@Categories.IncludeCategory({Critical.class})
public interface Critical {
}
