Reflection 1
While implementing the Edit and Delete features, I applied some Clean Code principles which are :

- Giving proper names that represent what does the variable and function made with their purpose.

- I also apply the single responsibility principle where each file focus on their job based on their name, for example the ProductRepository file solely focus on the data manipulation, the Product file in model is used to store the product model.

Secure coding practices that I attempted are the Secure HTTP methods where I used @PostMapping for Delete and Edit rather than @GetMapping. It will ensure that the state-changing operation cannot be triggered by Cross-Site Request Forgery as easy as GET request. I also used random UUID for product ID to make it more secure rather than sequential integer (1,2,3,4,5,6,7.....).

Reflection 2

Writing unit test give me a little bit of confidence in my code that it will works well. I don't think there is a fixed number of unit test that we need to make to verify our program, the focus we need to do is to ensure that every cases are already covered up. 100% Code coverage does not mean that we don't have any bugs or error but it only means that we tested every single line of our code. It does not directly means that our code does not have bugs. 

By creating a new Java class similar to the prior functional test will reduce the code quality because the code will have "Code Duplication". By this problem, if in the future I need to do maintenance, I might need to change a lot of exact same line several times manually in every single file. Thus, to improve the cleanliness of the code, I should gather the common logic of the code into a new class and make it as the baseline to the other file that need it.