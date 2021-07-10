package tororoguiapi.tororoguiapi

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

object GUIAPI {

    fun createitem(material: Material, name: String): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta


        meta.displayName(Component.text(name.replace("&","§").replace("/"," ")))
        item.itemMeta = meta
        return item
    }

    fun createitem(material: Material, name: String, lore : List<String>): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta

        meta.displayName(Component.text(name.replace("&","§").replace("/"," ")))
        val loresave = ArrayList<Component>()
        for (i in lore){
            loresave.add(Component.text(i.replace("&","§").replace("/"," ")))
        }
        meta.lore(loresave)
        item.itemMeta = meta
        return item
    }

    fun createitem(material: Material, lore : List<String>): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        val loresave = ArrayList<Component>()
        for (i in lore){
            loresave.add(Component.text(i.replace("&","§").replace("/"," ")))
        }
        meta.lore(loresave)
        item.itemMeta = meta
        return item
    }

    fun createitem(material: Material, lore : List<String>,csm: Int): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        val loresave = ArrayList<Component>()
        for (i in lore){
            loresave.add(Component.text(i.replace("&","§").replace("/"," ")))
        }
        meta.lore(loresave)
        meta.setCustomModelData(csm)
        item.itemMeta = meta
        return item
    }

    fun createitem(material: Material, name: String, csm : Int): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        meta.displayName(Component.text(name.replace("&","§").replace("/"," ")))
        meta.setCustomModelData(csm)
        item.itemMeta = meta
        return item
    }

    fun createitem(material: Material, name: String, lore : List<String>, csm : Int): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        meta.displayName(Component.text(name.replace("&","§").replace("/"," ")))
        val loresave = ArrayList<Component>()
        for (i in lore){
            loresave.add(Component.text(i.replace("&","§").replace("/"," ")))
        }
        meta.lore(loresave)
        meta.setCustomModelData(csm)
        item.itemMeta = meta
        return item
    }

    fun createitem(material: Material, csm: Int): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta
        meta.setCustomModelData(csm)
        item.itemMeta = meta
        return item
    }

    fun ItemStack.addEnchant(enchantment: Enchantment, level : Int): ItemStack {
        val meta = this.itemMeta
        meta.addEnchant(enchantment,level,false)
        this.itemMeta = meta
        return this
    }

    fun ItemStack.addItemFlag(itemFlag: ItemFlag): ItemStack {
        val meta = this.itemMeta
        meta.addItemFlags(itemFlag)
        this.itemMeta = meta
        return this
    }

    fun ItemStack.addAttribute(attribute: Attribute,attributeModifier: AttributeModifier): ItemStack {
        val meta = this.itemMeta
        meta.addAttributeModifier(attribute,attributeModifier)
        this.itemMeta = meta
        return this
    }

    fun ItemStack.setNBT(namespacedKey: String,int: Int):ItemStack{
        val meta = this.itemMeta
        meta.persistentDataContainer.set(NamespacedKey.fromString(namespacedKey)!!, PersistentDataType.INTEGER,int)
        this.itemMeta = meta
        return this
    }

    fun ItemStack.setNBT(namespacedKey: String,string: String):ItemStack{
        val meta = this.itemMeta
        meta.persistentDataContainer.set(NamespacedKey.fromString(namespacedKey)!!, PersistentDataType.STRING,string)
        this.itemMeta = meta
        return this
    }

    fun ItemStack.setNBT(namespacedKey: String,double: Double):ItemStack{
        val meta = this.itemMeta
        meta.persistentDataContainer.set(NamespacedKey.fromString(namespacedKey)!!, PersistentDataType.DOUBLE,double)
        this.itemMeta = meta
        return this
    }

    fun ItemStack.setNBT(namespacedKey: String,float: Float):ItemStack{
        val meta = this.itemMeta
        meta.persistentDataContainer.set(NamespacedKey.fromString(namespacedKey)!!, PersistentDataType.FLOAT,float)
        this.itemMeta = meta
        return this
    }

    fun Inventory.fillitem(slot : IntRange, item : ItemStack): Boolean {
        if (slot.first < 0 || slot.last > 54)return false
        for (i in slot){
            this.setItem(i,item)
        }
        return true
    }

    fun Inventory.alladditem(item : ItemStack,amount: Int): Int {
        var amount2 = amount
        while(amount2!=0){

            val a = if(amount2 >= item.type.maxStackSize) item.type.maxStackSize else amount2
            item.amount = a
            val returnitem = this.addItem(item)
            if (returnitem.isNotEmpty())return amount2 - returnitem.getValue(0).amount
            amount2 -= a

        }
        return 0
    }

    fun creategui(name: String,line:Int) : Inventory{
        if (line !in 1..6)return Bukkit.createInventory(null,54, Component.text(name))
        return Bukkit.createInventory(null,line*9, Component.text(name))
    }


}