Feature: Perform Login and logout on Facebook

  Scenario Outline: Perform Login and logout on Facebook
    Given the "<Browser>" and navigate to the web site www.facebook.com.br
    And Make the "<Login>" and "<password>"
    When Validate the "<Name>" in Main menu
    Then Make the logout on facebook and validate the text "<Logout>".

    Examples: 
      | Browser | Login                         | Password | Name              | Logout               |
      | Firefox | reiload@yahoo.com.br          |          | Reinaldo Rossetti | Log In or Sign Up    |
      | Firefox | reinaldo.rossetti@outlook.com |          | Reinaldo Junior   | entre ou cadastre-se |
      | IE      | reiload@yahoo.com.br          |          | Reinaldo Rossetti | Log In or Sign Up    |
      | IE      | reinaldo.rossetti@outlook.com |          | Reinaldo Junior   | entre ou cadastre-se |
