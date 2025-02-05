# Social Network Project

This project is a social network application designed for students worldwide to connect and interact. It includes various features such as user management, post management, and user interactions. The project is implemented in Java using the Swing library for the graphical user interface.

## Features

- **User Management:**
  - User registration and login with `Username` and `Password`.
  - Each user has a personalized profile page.
  - Users can follow each other.
  - Users can edit or delete their personal information and posts.
  - Users can delete their accounts.

- **Post Management:**
  - Users can add, view, edit, and delete posts.
  - Each post is displayed on the user's profile page.

- **User Interaction:**
  - The system suggests potential friends based on mutual followers.
  - If a user has no mutual followers, the system suggests newly joined users.

## Project Structure

### Main Classes

- **Main:** The entry point of the application. It initializes the main window.
- **Connector:** Manages connections between users.
- **Network:** Manages the network of users and their connections.
- **User:** Represents a user in the social network, managing personal information, posts, and connections.

### Abstract Classes

- **IConnector:** Interface for managing connections.
- **INetwork:** Interface for network operations.
- **IUser:** Interface for user-related operations.

### User Interface Classes

- **HomePage:** The main dashboard after logging in.
- **Add_Posts:** Allows users to add new posts.
- **Delete_Post:** Allows users to delete their posts.
- **Edit_Posts:** Allows users to edit their existing posts.
- **EditInfo:** Allows users to edit their personal information.
- **DeleteAccount:** Allows users to delete their accounts.
- **EditPassword:** Allows users to change their passwords.
- **FollowerPage:** Displays the list of followers.
- **FollowingPage:** Displays the list of users being followed.
- **LogIn:** Handles user login functionality.
- **SignUp:** Handles user registration functionality.
- **View_Posts:** Displays the posts of the user.
- **UserPanel:** The main user interface after logging in.
- **PostPage:** A page for viewing and managing posts.
- **StartPage:** The initial page where users can log in or register.
- **OtherHomePage:** Displays the home page of other users.

## How to Run

1. Ensure you have Java Development Kit (JDK) installed on your system.
2. Clone the repository or download the source code.
3. Compile the Java files:
   ```bash
   javac Main.java MainPage.java Add_Posts.java Delete_Post.java Edit_Posts.java EditInfo.java DeleteAccount.java Connector.java EditPassword.java FollowerPage.java FollowingPage.java HomePage.java IConnector.java INetwork.java IUser.java LogIn.java OtherHomePage.java PostPage.java SignUp.java Network.java UserPanel.java View_Posts.java User.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

## Screenshots

Below are some screenshots of the application interface. You can replace the links with actual images of your application.

![Main Page](https://via.placeholder.com/300)  
![Add Post](https://via.placeholder.com/300)  
![Edit Post](https://via.placeholder.com/300)  
![Delete Post](https://via.placeholder.com/300)  
![Edit Info](https://via.placeholder.com/300)  
![Delete Account](https://via.placeholder.com/300)  
![Edit Password](https://via.placeholder.com/300)  
![Follower Page](https://via.placeholder.com/300)  
![Following Page](https://via.placeholder.com/300)  
![Home Page](https://via.placeholder.com/300)  
![LogIn Page](https://via.placeholder.com/300)  
![SignUp Page](https://via.placeholder.com/300)  
![OtherHomePage](https://via.placeholder.com/300)  
![PostPage](https://via.placeholder.com/300)  
![UserPanel](https://via.placeholder.com/300)  
![View_Posts](https://via.placeholder.com/300)  

## Contributing

If you wish to contribute to this project, please create an Issue or submit a Pull Request. All contributions are welcome.

## License

This project is licensed under the MIT License. For more information, see the [LICENSE](LICENSE) file.

---

We hope this social network application is useful for you. If you have any questions or suggestions, feel free to share them via Issues.
```
