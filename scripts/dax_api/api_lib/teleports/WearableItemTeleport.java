package scripts.dax_api.api_lib.teleports;

import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;
import scripts.dax_api.shared.helpers.RSItemHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class WearableItemTeleport {

	public static final Predicate<RSItem> RING_OF_WEALTH_FILTER = Filters.Items.nameContains("Ring of wealth (").and(i -> i.getDefinition().getName().matches(".*[1-5]\\)"));
	public static final Predicate<RSItem> RING_OF_DUELING_FILTER = Filters.Items.nameContains("Ring of dueling");
	public static final Predicate<RSItem> NECKLACE_OF_PASSAGE_FILTER = Filters.Items.nameContains("Necklace of passage");
	public static final Predicate<RSItem> COMBAT_BRACE_FILTER = Filters.Items.nameContains("Combat bracelet(");
	public static final Predicate<RSItem> GAMES_NECKLACE_FILTER = Filters.Items.nameContains("Games necklace");
	public static final Predicate<RSItem> GLORY_FILTER = Filters.Items.nameContains("glory").and(Filters.Items.nameContains("eternal","("));
	public static final Predicate<RSItem> SKILLS_FILTER = Filters.Items.nameContains("Skills necklace(");
	public static final Predicate<RSItem> BURNING_AMULET_FILTER = Filters.Items.nameContains("Burning amulet(");
	public static final Predicate<RSItem> DIGSITE_PENDANT_FILTER = Filters.Items.nameContains("Digsite pendant");
	public static final Predicate<RSItem> TELEPORT_CRYSTAL_FILTER = Filters.Items.nameContains("Teleport crystal");
	public static final Predicate<RSItem> XERICS_TALISMAN_FILTER = Filters.Items.nameEquals("Xeric's talisman");
	public static final Predicate<RSItem> RADAS_BLESSING_FILTER = Filters.Items.nameContains("Rada's blessing");
	public static final Predicate<RSItem> CRAFTING_CAPE_FILTER = Filters.Items.nameContains("Crafting cape");
	public static final Predicate<RSItem> EXPLORERS_RING_FILTER = Filters.Items.nameContains("Explorer's ring");
	public static final Predicate<RSItem> QUEST_CAPE_FILTER = Filters.Items.nameContains("Quest point cape");
	public static final Predicate<RSItem> ARDOUGNE_CLOAK_FILTER = Filters.Items.nameContains("Ardougne cloak");
	public static final Predicate<RSItem> CONSTRUCTION_CAPE_FILTER = Filters.Items.nameContains("Construct. cape");


	private WearableItemTeleport() {

	}

	public static boolean has(Predicate<RSItem> filter) {
		return Inventory.find(filter).length > 0 || Equipment.find(filter).length > 0;
	}

	public static boolean teleport(Predicate<RSItem> filter, String action) {
		return teleportWithItem(filter,action);
	}

	private static boolean teleportWithItem(Predicate<RSItem> itemFilter, String regex) {
		ArrayList<RSItem> items = new ArrayList<>();
		items.addAll(Arrays.asList(Inventory.find(itemFilter)));
		items.addAll(Arrays.asList(Equipment.find(itemFilter)));

		if (items.size() == 0) {
			return false;
		}

		RSItem teleportItem = items.get(0);
		return RSItemHelper.clickMatch(teleportItem, "(Rub|" + regex + ")");
	}

}