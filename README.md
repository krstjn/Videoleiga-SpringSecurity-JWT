Video-leiga-support-session

Changes that were made from the original solution(https://github.com/krstjn/Video-leiga-support-session-REST) include:

+ The [Security](src/main/java/is/hi/hbv501/videoleiga/videoleiga/Security) folder
+ The [JwtUserDetailService](src/main/java/is/hi/hbv501/videoleiga/videoleiga/Services/Implementations/JwtUserDetailsService.java)
+ The [AuthenticationController](src/main/java/is/hi/hbv501/videoleiga/videoleiga/Controllers/AuthenticationController.java) has been setup to handle authentication
+ A minor change was made in the [User](src/main/java/is/hi/hbv501/videoleiga/videoleiga/Entities/User.java) entity to make requests easier
+ Clean-up unused entities such as Spring Session
+ Change how to access logged in user in [HomeController](src/main/java/is/hi/hbv501/videoleiga/videoleiga/Controllers/HomeController.java)