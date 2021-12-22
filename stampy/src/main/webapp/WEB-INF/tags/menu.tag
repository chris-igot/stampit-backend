<%@ tag language="java" pageEncoding="UTF-8"%>

<nav class="navbar fixed-top navbar-expand-md navbar-dark bg-pblue">
    <div class="container-fluid">
        <a class="navbar-brand" href="/public">Stampeet!</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarDark" aria-controls="navbarDark" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
     <div class="collapse navbar-collapse" id="navbarDark">
         <ul class="navbar-nav me-auto mb-2 mb-xl-0">
             <li class="nav-item">
                 <a class="nav-link active" href="/home">Home</a>
             </li>
             <li class="nav-item">
                 <a class="nav-link" href="/post/new">New Post</a>
             </li>
             <li class="nav-item">
                 <a class="nav-link" href="/home/edit">Edit Profile</a>
             </li>
             <li class="nav-item">
                 <a class="nav-link" href="/profile/follows">Following</a>
             </li>
             <li class="nav-item">
                 <a class="nav-link" href="/search">Search</a>
             </li>
             <jsp:doBody/>
         </ul>
         <a class="btn btn-sm link-light" href="/logout">logout</a>
     </div>
    </div>
</nav>
<br /><br /><br />