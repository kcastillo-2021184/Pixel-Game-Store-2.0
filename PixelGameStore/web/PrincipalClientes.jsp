<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu Principal</title>  
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css " integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="PrincipalCSS.css">
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap');
            
            .pixel-game {
                font-family: 'Press Start 2P', cursive;
                color: #4d9de0;
                font-size: 24px;
                text-shadow: 2px 2px #1a1a2e, -2px -2px #1a1a2e, 2px -2px #1a1a2e, -2px 2px #1a1a2e;
                animation: pixelate 5s infinite alternate;
                margin-right: 30px;
            }

            @keyframes pixelate {
                0% { transform: translate(0, 0); }
                25% { transform: translate(2px, 2px); }
                50% { transform: translate(-2px, -2px); }
                75% { transform: translate(-2px, 2px); }
                100% { transform: translate(2px, -2px); }
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-info">
            <div class="collapse navbar-collapse" id="navbarNav">
              <ul class="navbar-nav">
                <li class="nav-item ">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="Controlador?menu=Home" target="myFrame">Home</a>
                </li>
                <li class="nav-item">
                    <a style="margin-left: 10px; border: none" class="btn btn-outline-light" href="" target="myFrame">Tienda</a>
                </li>
              </ul>
            </div>
            <div class="dropdown">
                <button style="border: none" class="btn btn-outline-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  ${usuario.getNombreUsuario()}
                </button>
                <div class="dropdown-menu dropdown-menu-right text-center" aria-labelledby="dropdownMenuButton">
                    <a class="drop-item" href="#">
                        <img src="img/${usuario.getUsuarioLogin()}.png" alt="60" width="60"/>
                    </a>
                    <a class="dropdown-item">${usuario.getNombreUsuario()}</a>
                    <a class="dropdown-item">${usuario.getApellidoUsuario()}</a>
                    <a class="dropdown-item">${usuario.getUsuarioLogin()}</a>
                    <div class="dropdown-divider"></div>
                    <form action="Validar" method="POST">
                        <button name="accion" name="Salir" class="dropdown-item">Salir</button>
                    </form>
                </div>
            </div>       
          </nav>
        
        
                  <div class="m-4" style="height: 850px">
                      <iframe name="myFrame" style="height:100%; width:100%;border:none"></iframe>
                  </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body>
</html>



