package packmenu;

/**
 * Main menu options
 * @author Oihan and Eneko
 */
enum MainMenuOption {
	READY, LD_PEOPLE, LD_RELATIONS, PRINT_PEOPLE, PRINT_RELATIONS, SEARCH, 
	GROUP_BY_MOVIES, SHORTEST_PATH, LONGEST_PATH, CLIQUES, EXTRAS, QUIT;
}

/**
 * Search menu options
 * @author Oihan and Eneko
 */
enum SearchMenuOption {
	READY, BY_LAST_NAME, BY_CITY, BETWEEN_TWO_DATES, BY_RESIDENCE_FILE, BACK_TO_MAIN_MENU;
}

/**
 * Extras menu options
 * @author Oihan and Eneko
 */
enum ExtrasMenuOption {
	READY, GENERATE_RANDOM_PEOPLE, GENERATE_RANDOM_RELATIONS, BACK_TO_MAIN_MENU;
}

