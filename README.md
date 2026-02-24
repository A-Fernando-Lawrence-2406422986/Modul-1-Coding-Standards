Reflection 1
While implementing the Edit and Delete features, I applied some Clean Code principles which are :

- Giving proper names that represent what does the variable and function made with their purpose.

- I also apply the single responsibility principle where each file focus on their job based on their name, for example the ProductRepository file solely focus on the data manipulation, the Product file in model is used to store the product model.

Secure coding practices that I attempted are the Secure HTTP methods where I used @PostMapping for Delete and Edit rather than @GetMapping. It will ensure that the state-changing operation cannot be triggered by Cross-Site Request Forgery as easy as GET request. I also used random UUID for product ID to make it more secure rather than sequential integer (1,2,3,4,5,6,7.....).

Reflection 2

Writing unit test give me a little bit of confidence in my code that it will works well. I don't think there is a fixed number of unit test that we need to make to verify our program, the focus we need to do is to ensure that every cases are already covered up. 100% Code coverage does not mean that we don't have any bugs or error but it only means that we tested every single line of our code. It does not directly means that our code does not have bugs.

By creating a new Java class similar to the prior functional test will reduce the code quality because the code will have "Code Duplication". By this problem, if in the future I need to do maintenance, I might need to change a lot of exact same line several times manually in every single file. Thus, to improve the cleanliness of the code, I should gather the common logic of the code into a new class and make it as the baseline to the other file that need it.

Reflection module 2



Issue that I fixed was a report by SonarCloud stating "Add at least one assertion to this test case". This occurred because the main() method was called inside a test without verifying any output. To fix this, I call an assertDoesNotThrow(() -> EshopApplication.main(new String[] {})); so that it satisfies SonarCloud requirement that every test must have an assertion.



The current implementation already meets the definition of both Continuous Integration (CI) and Continuous Deployment (CD). For CI, the GitHub actions workflow (ci.yml and sonarcloud.yml) automatically build the project, run unit test and perform code quality analysis every time a new code it push or a pull request is opened. This will make sure the code is validated and functionable. For CD, the system is fully automated through the Koyeb's pull-based integration where every validated code is merged to main branch, the PaaS automatically detects the change, build new Docker image and deploy the application.