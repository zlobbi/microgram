<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Microgram</title>
</head>
<body>
  <div class="container">
      <div class="days flex flex-wrap">
        <#list users as user>
            <p>---------------------User-------------------</p>
            <p>Login: ${user.login}</p>
            <p>Email: ${user.email}</p>
            <#list user.publications as pub>
                <p>---------------------Publication-------------------</p>
                <p>Publication: ${pub.image}</p>
                <p>description: ${pub.description}</p>
                <p>likes ${pub.getLikesCount()}</p>
                <p>-----------------------Comments--------------------</p>
                <#list pub.comments as com>
                    <p> ${com.date} User: ${com.commentator.login}: ${com.text}</p>
                </#list>
            </#list>
            <p>--------------------------------------------------------</p>
        </#list>
      </div>
    </div>
  </div>
</body>
</html>