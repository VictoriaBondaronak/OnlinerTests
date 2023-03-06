Feature: Onliner

  Scenario:
    Given Go to "https://www.onliner.by/"
    When I navigate to the "Каталог" page
    And Select "Компьютеры и сети" from the navigation menu
    And  In the submenu navigate to "Ноутбуки, компьютеры, мониторы"
    And Go to the "Игровые ноутбуки"