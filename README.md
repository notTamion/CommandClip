# CommandClip [![CommandClip Build Status](https://img.shields.io/github/actions/workflow/status/notTamion/CommandClip/publish.yml)](https://github.com/notTamion/CommandClip/actions) [![Latest Version](https://img.shields.io/maven-central/v/io.github.nottamion/commandclip)](`https://central.sonatype.com/artifact/io.github.nottamion/commandclip/overview`)

A Command Framework for Bukkit Plugins

## Dependency

##### Gradle
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    compileOnly("io.github.nottamion:commandclip:1.0.0")
}
```

##### Maven
```xml
<dependency>
    <groupId>io.github.nottamion</groupId>
    <artifactId>commandclip</artifactId>
    <version>1.0.0</version>
</dependency>
```

To reduce the file size of your plugin its recommended to let spigot download the framework 
for you. This also removes the need to shade the framework into your plugin jar.
##### plugin.yml
```yaml
libraries:
  - io.github.nottamion:commandclip:1.0.0
```

## Usage

Documentation is currently being built at https://github.com/notTamion/CommandClip/wiki.

For now just take a look at the example below.

Here is an example utilizing basic features:
```java
new BaseCommand("hello", this)
    .subCommand(new SubCommand("there")
        .executes((commandSender, s, strings) -> {
            commandSender.sendMessage(s + " there " + strings[0]);
        })
        .tabCompletes((commandSender, s, strings) -> strings.length == 1 ? Bukkit.getOnlinePlayers().stream().map(Player::getName).toList(): List.of())
        .testArgs((s, strings) -> {
            if (strings.length != 1)
                return "Please provide a valid playername";
            if (!Bukkit.getOnlinePlayers().stream().map(player -> player.getName()).toList().contains(strings[0]))
                return "Player is not online";
            return null;
        })
        .permission("hello.there", "You aren't allowed to get a Welcome :("))
    .alias("hi")
    .commandDescription("Welcomes you")
    .commandPermission("hello")
    .register();
```