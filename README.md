# CommandClip [![CommandClip Build Status](https://img.shields.io/github/actions/workflow/status/notTamion/CommandClip/publish.yml)](https://github.com/notTamion/CommandClip/actions)
A Command Framework for Bukkit Plugins

Bukkit plugin.yml:
```yaml
libraries:
  - io.github.nottamion:commandclip:1.0.0
```

Gradle:
```kotlin
compileOnly("io.github.nottamion:commandclip:1.0.0")
```

To start create a new BaseCommand and just look at the methods it contains. Everything you need to 
know should be in the javadocs.

I will make proper docs once i have time

Here is an example:
```java
new BaseCommand("hello", this)
    .subCommand(new SubCommand("there")
        .executes((commandSender, alias, strings) -> {
            commandSender.sendMessage(alias + " there " + strings[0]);
        })
        .tabCompletes((commandSender, alias, strings) -> strings.length == 1 ? Bukkit.getOnlinePlayers().stream().map(Player::getName).toList(): List.of())
        .testArgs((alias, strings) -> {
            if (strings.length != 1)
                return "Please provide a valid Player name";
            if (!Bukkit.getOnlinePlayers().stream().map(Player::getName).toList().contains(strings[0]))
                return strings[0] + " is not on the server";
            return null;
        }))
    .alias("hi")
    .commandDescription("Gives you a Welcome :)")
    .register();
```