package cn.nukkit.item;

import cn.nukkit.item.Item;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.ListTag;

import java.util.concurrent.ThreadLocalRandom;

public class ItemBookWritten extends Item {

    protected boolean isWritten = false;

    public ItemBookWritten() {
        super (Item.WRITTEN_BOOK);
    }

    public ItemBookWritten (String author, String title, String[] pages) {
        super (Item.WRITTEN_BOOK);
        this.writeBook(author, title, pages);
    }

    public Item writeBook (String author, String title, String[] pages) {
        ListTag<CompoundTag> pageList = new ListTag<>("pages");
        for (String page : pages){
            pageList.add(new CompoundTag().putString("photoname", "").putString("text", page));
        }
        return writeBook(author, title, pageList);
    }
    public Item writeBook (String author, String title, ListTag<CompoundTag>  pages){
        if (pages.size() > 50 || pages.size() <= 0) return this; //Minecraft does not support more than 50 pages
        if (this.isWritten) return this; //Book content can only be updated once
        CompoundTag tag;
        if (!this.hasCompoundTag()){
            tag = new CompoundTag();
        }
        else {
            tag = this.getNamedTag();
        }

        tag.putString("author", author);
        tag.putString("title", title);
        tag.putList(pages);

        tag.putInt("generation", 0);
        long randomId = 1095216660480L + ThreadLocalRandom.current().nextLong(0L, 2147483647L);
        tag.putLong("id", randomId);

        this.isWritten = true;
        return this.setNamedTag(tag);
    }

}
