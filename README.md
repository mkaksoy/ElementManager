# <p align="center">Element Manager</p>
<p align="center">
<img src="https://cdn.modrinth.com/data/z2AiNsm2/images/cf16b1677aa8cf9a190cc316cd11064fdf9f1dfd.png" width="250" height="250" alt="">
</p>

<p align="center">A lightweight plugin that simplifies server management in Minecraft 🛠️</p>
<br>

---

<br>

# What is Element Manager?
**Element Manager** is a powerful and lightweight server management plugin for **Minecraft**. It provides essential features for server administrators, such as automatic restart functionality, configuration management, and more.

This plugin allows for easy management of server settings and ensures your server stays running smoothly with auto-restart capabilities. The plugin is designed to work with both **Bukkit** and **Paper** servers.

## Features

- **Automatic Server Restart**: Automatically restarts your Minecraft server based on the settings in the `config.yml` file.
- **Easy Configuration**: Manage server settings like memory allocation, server jar file, and GUI options through a simple `config.yml`.
- **Folder and File Management**: Automatically creates the `management` folder and the `config.yml` file upon first run.
- **Cross-Platform Support**: Works across all platforms (Windows, Linux, macOS) with tailored commands for each.
- **Log and Backup Management**: Easily manage backups and logs with auto-created directories.

## Installation

1. Download the latest version of **Element Manager** from the [releases](https://github.com/yourusername/ElementManager/releases) page.
2. Place the `.jar` file in the **plugins** folder of your **Minecraft** server.
3. Start your server. The plugin will automatically create a `management` folder and a `config.yml` file if they do not already exist.
4. Configure the settings in the `config.yml` file as per your needs.

## Configuration

The plugin creates a `config.yml` file in the `management` folder on first run. Here is an example of the default configuration:

```yaml
options:                # Server Options
  auto-restart: true    # If enabled, it will automatically restart the server after it is stopped
logging:                # Logging Options
  chat:                 # Chat Logging Options
    enabled: true       # If true, the plugin will automatically log chat messages sent by the player
    forbidden:          # Add banned words to your servers (e.g. swearing, bad words). You can find out which players wrote these messages by examining the logs.
      - word1
      - word2
      - word3
server:                 # Server Properties
  jar: "server.jar"     # Name of the server JAR file
  memory: 4096          # The memory that your server uses (e.g. 4096MB = 4GB)
  gui: false            # If enabled, server starts with a GUI
```