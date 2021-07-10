package tororoguiapi.tororoguiapi

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import tororoguiapi.tororoguiapi.GUIAPI.alladditem
import tororoguiapi.tororoguiapi.GUIAPI.creategui
import tororoguiapi.tororoguiapi.GUIAPI.createitem
import java.util.*

class TororoGUIAPI : JavaPlugin() {


    override fun onEnable() {
        getCommand("tororogui")?.setExecutor(this)
        server.logger.info(creategui("test",1).alladditem(ItemStack(Material.ACACIA_BUTTON),1000).toString())
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player)return true

        if (args.isEmpty()){
            sender.sendMessage("/tororogui create ...")
            sender.sendMessage("手に持ったアイテムの内容を変更します")
            sender.sendMessage("(/)は空白、(\\)は改行、&はカラコです")
            return true
        }


        when(args[0]){

            "create"->{
                if (args.size < 3)return false
                if (sender.inventory.itemInMainHand.type == Material.AIR)return false
                when(args[1]){
                    "name"->{
                        if (args.size != 3)return false
                        sender.inventory.setItemInMainHand(createitem(sender.inventory.itemInMainHand.type,args[2]))
                    }

                    "namelore"->{
                        if (args.size != 4)return false
                        sender.inventory.setItemInMainHand(createitem(sender.inventory.itemInMainHand.type,args[2],args[3].split("\\")))
                    }

                    "namelorecsm"->{
                        if (args.size != 5)return false
                        sender.inventory.setItemInMainHand(createitem(sender.inventory.itemInMainHand.type,args[2],args[3].split("\\"),args[4].toIntOrNull()?:return false))
                    }

                    "namecsm"->{
                        if (args.size != 4)return false
                        sender.inventory.setItemInMainHand(createitem(sender.inventory.itemInMainHand.type,args[2],args[3].toIntOrNull()?:return false))
                    }

                    "lore"->{
                        if (args.size != 3)return false
                        sender.inventory.setItemInMainHand(createitem(sender.inventory.itemInMainHand.type,args[2].split("\\")))
                    }

                    "lorecsm"->{
                        if (args.size != 4)return false
                        sender.inventory.setItemInMainHand(createitem(sender.inventory.itemInMainHand.type,args[2].split("\\"),args[3].toIntOrNull()?:return false))
                    }

                    "csm"->{
                        if (args.size != 3)return false
                        sender.inventory.setItemInMainHand(createitem(sender.inventory.itemInMainHand.type,args[2].toIntOrNull()?:return false))
                    }

                }
            }

        }


        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        if (alias == "tororogui"){
            if (args.size == 1)return mutableListOf("create")

            if (args[0] == "create"){
                if (args.size == 2)return mutableListOf("name","namelore","namelorecsm","namecsm","lore","lorecsm","csm")
                when(args[1]){
                    "name"->if (args.size == 3)return mutableListOf("名前")
                    "namelore"->{
                        if (args.size == 3)return mutableListOf("名前")
                        if (args.size == 4)return mutableListOf("説明(\\で分割)")

                    }
                    "namelorecsm"->{
                        if (args.size == 3)return mutableListOf("名前")
                        if (args.size == 4)return mutableListOf("説明(\\で分割)")
                        if (args.size == 5)return mutableListOf("カスタムモデルデータ")
                    }
                    "namecsm"->{
                        if (args.size == 3)return mutableListOf("名前")
                        if (args.size == 4)return mutableListOf("カスタムモデルデータ")
                    }
                    "lore"->if (args.size == 3)return mutableListOf("説明(\\で分割)")
                    "lorecsm"->{
                        if (args.size == 3)return mutableListOf("説明(\\で分割)")
                        if (args.size == 4)return mutableListOf("カスタムモデルデータ")
                    }
                    "csm"->if (args.size == 3)return mutableListOf("カスタムモデルデータ")
                    else ->return Collections.emptyList()
                }
            }


        }

        return Collections.emptyList()
    }

}