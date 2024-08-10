package me.se7manopla;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileAPI {

    private Main plugin;
    private File file;
    private FileConfiguration config;
    private String path;
    private String folderpath;

    public FileAPI(Main plugin, String path, String folderpath) {
        this.plugin = plugin;
        this.path = path;
        this.folderpath = folderpath;
        this.config = null;
        this.file = null;
    }

    public String getFolderPath() {
        return this.folderpath;
    }

    public void create() {
        this.file = new File(plugin.getDataFolder() + File.separator + this.folderpath, this.path);
        if (!this.file.exists()) {
            try {
                if (this.file.createNewFile()) {
                    getConfig().options().copyDefaults(true);
                    saveConfig();
                } else {
                    System.out.println("Falha ao criar o arquivo: " + this.path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            reloadConfig();
        }
    }


    public FileConfiguration getConfig() {
        if (this.config == null) {
            reloadConfig();
        }
        
        return this.config;
    }

    public void saveConfig() {
        try {
            this.config.save(this.file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void reloadConfig() {
        if (this.config == null) {
            this.file = new File(plugin.getDataFolder() + File.separator + this.folderpath, this.path);
        }
        
        this.config = YamlConfiguration.loadConfiguration(this.file);
        
        if (plugin.getResource(this.path) != null && this.file.length() == 0) {
            try (Reader defaultConfigStream = new InputStreamReader(plugin.getResource(this.path), "UTF8")) {
                YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
                config.setDefaults(defaultConfig);
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getPath() {
        return this.path;
    }
}
