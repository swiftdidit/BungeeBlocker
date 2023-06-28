# BungeeBlocker
## BungeeCord Server Version Whitelister (Version Blocker)
### Spigot Download: https://www.spigotmc.org/resources/bungeeblocker.110649/

#### What is this used for?:
This plugin can be used to ALLOW ONLY for the versions you set up/list in the 'config.yml' file to be able to connect to your server.

#### How to Use:
Simply drop inside the 'plugins' folder
Start/Restart the Proxy Server
Open the 'BungeeBlocker' folder, open 'config.yml'
Fill in your BungeeCord server's names exactly as you have in your BungeeCord 'config.yml'
​
<h3 style="color:#AA0000;">ALERT!</h3> <h4>When filling out our 'config.yml' file you must write the server names exactly how you did in your BungeeCord config.</h4>

<details>
  <summary>Click me: Example Config Server Setup</summary>
  
servers: #you may not list the server, and BungeeBlocker will not interact with it
  anarchy: #only allows for the 4 following versions too connect
    - "762" #will be read as 1.19.4
    - "757"
    - "755"
    - "1.20" #will be read as 763
  teams: #only allows two versions too connect
    - "1.8" #will be read as 47
    - "5" #1.7
  
</details>

Please provide me with any suggestions you have that you believe can make this plugin more useful. Keep it related.

The plugin will not work unless you have a 'BungeeCord.jar' proxy server
