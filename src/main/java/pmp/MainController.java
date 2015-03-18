package pmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import pmp.entresuelo.core.Category;
import pmp.entresuelo.core.CategoryDetails;
import pmp.entresuelo.core.InventoryDetails;
import pmp.entresuelo.core.Item;
import pmp.entresuelo.core.ItemAdder;
import pmp.entresuelo.core.Location;
import pmp.entresuelo.core.SimpleItemAdder;
import pmp.entresuelo.service.AbstractManager;
import pmp.entresuelo.service.ItemAndDetailsManager;
import pmp.entresuelo.service.validation.CategoryValidator;
import pmp.entresuelo.service.validation.ItemAdderValidator;
import pmp.entresuelo.service.validation.ItemValidator;
import pmp.entresuelo.service.validation.LocationValidator;
import pmp.entresuelo.service.validation.SimpleItemAdderValidator;
import pmp.entresuelo.service.impl.CategoryDetailsManager;
import pmp.entresuelo.service.impl.CategoryManager;
import pmp.entresuelo.service.impl.InventoryDetailsManager;
import pmp.entresuelo.service.impl.ItemManager;
import pmp.entresuelo.service.impl.LocationManager;

@Controller
public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);

    @Autowired
    ItemAndDetailsManager itemManager;

//    @Autowired
//    AbstractManager itemManager;
//    ItemManager itemManager;

    @Autowired
    AbstractManager locationManager;
//    LocationManager locationManager;

    @Autowired
    AbstractManager categoryManager;
//    CategoryManager categoryManager;

    @Autowired
    AbstractManager categoryDetailsManager;
//    CategoryDetailsManager categoryDetailsManager;

    @Autowired
    AbstractManager inventoryDetailsManager;
//    InventoryDetailsManager inventoryDetailsManager;

    @Autowired
    ItemValidator itemValidator;

    @Autowired
    LocationValidator locationValidator;

    @Autowired
    CategoryValidator categoryValidator;

    @Autowired
    SimpleItemAdderValidator itemAdderValidator;

    @InitBinder("addItem")
    protected void initAddItemBinder(WebDataBinder binder) {
        binder.addValidators(this.itemValidator);
    }   // end initBinder();

    @InitBinder("addLocation")
    protected void initAddLocationBinder(WebDataBinder binder) {
        binder.addValidators(this.locationValidator);
    }   // end initBinder();

    @InitBinder("addCategory")
    protected void initAddCategoryBinder(WebDataBinder binder) {
        binder.addValidators(this.categoryValidator);
    }   // end initBinder()

    @InitBinder("newItemWithDetails")
    protected void initNewItemWithDetailsBinder(WebDataBinder binder) {
        binder.addValidators(this.itemAdderValidator);
    }   // end initBinder()

    public MainController() {

    }	// public class MainController () {}   

//    public void setitemManager(AbstractManager manager) {
//    public void setItemManager(ItemManager manager) {
    public void setItemAndDetailsManager(ItemAndDetailsManager manager) {
        this.itemManager = manager;
    }	// end public void setManager(AbstractManager manager) {}

//    public void setLocationManager(AbstractManager manager) {
    public void setLocationManager(LocationManager manager) {
        this.locationManager = manager;
    }	// end public void setManager(AbstractManager manager) {}

//    public void setCategoryManager(AbstractManager manager) {
    public void setCategoryManager(CategoryManager manager) {
        this.categoryManager = manager;
    }	// end public void setManager(AbstractManager manager) {}

//    public void setCategoryDetailsManager(AbstractManager manager) {
    public void setCategoryDetailsManager(CategoryDetailsManager manager) {
        this.categoryDetailsManager = manager;
    }	// end public void setCategoryDetailsManager(AbstractManager manager) {}

//    public void setCategoryDetailsManager(AbstractManager manager) {
    public void setInventoryDetailsManager(InventoryDetailsManager manager) {
        this.inventoryDetailsManager = manager;
    }	// end public void setCategoryDetailsManager(AbstractManager manager) {}

    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView defaultPage() {

        ModelAndView model = new ModelAndView();
//        model.addObject("title", "Spring Security Login Form - Database Authentication");
//        model.addObject("message", "This is default page!");
//        model.setViewName("hello");
        model.setViewName("redirect:allItems");
        
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView adminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

//  for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        logger.debug("public ModelAndView accesssDenied() {}");

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        logger.debug(auth.getClass());

        if (!(auth instanceof AnonymousAuthenticationToken)) {
            logger.debug(auth.getPrincipal().getClass());

            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }	// end public ModelAndView accesssDenied() {}

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = {"/allItems"}, method = RequestMethod.GET)
    public ModelAndView allItems() {
        MainController.logger.debug(new Date() + " public ModelAndView allItems() {}");

        ModelAndView model = new ModelAndView();

//        List<Item> items = (List<Item>) itemManager.getAllEntities();
        List<Item> items = (List<Item>) itemManager.getAllItems();

        model.setViewName("allItems");
        model.addObject("items", items);

        MainController.logger.debug(items.size());

        MainController.logger.debug(items.get(0).getName());

        return model;
    }	// end public ModelAndView allItems() {}

    @RequestMapping(value = {"/allLocations"}, method = RequestMethod.GET)
    public ModelAndView allLocations() {
        MainController.logger.debug(new Date() + " public ModelAndView allLocations() {}");

        ModelAndView model = new ModelAndView();

        List<Location> locations = (List<Location>) locationManager.getAllEntities();

        model.setViewName("allLocations");
        model.addObject("locations", locations);

        MainController.logger.debug(locations.size());

        MainController.logger.debug(locations.get(0).getName());

        return model;
    }	// end public ModelAndView allItems() {}

    @RequestMapping(value = {"/allCategories"}, method = RequestMethod.GET)
    public ModelAndView allCategories() {
        MainController.logger.debug(new Date() + " public ModelAndView allCategories() {}");

        ModelAndView model = new ModelAndView();

        List<Category> categories = (List<Category>) categoryManager.getAllEntities();

        model.setViewName("allCategories");
        model.addObject("categories", categories);

        MainController.logger.debug(categories.size());

        MainController.logger.debug(categories.get(0).getName());

        return model;
    }	// end public ModelAndView allItems() {}

    @RequestMapping(value = {"allCategoryDetails"}, method = RequestMethod.GET)
    public ModelAndView allCategoryDetails() {
        MainController.logger.debug(new Date() + " public ModelAndView allCategoryDetails() {}");

        ModelAndView model = new ModelAndView();
        model.setViewName("allCategoryDetails");

//      TODO it seems, that using getAllEntities() for CategoryDetails is error prone;
//      category records for particular item may not go one by one,
//      what is essential for CategoryDetailsExtractor        
        List<CategoryDetails> categoryDetails = (List<CategoryDetails>) categoryDetailsManager.getAllEntities();

        model.addObject("categoryDetails", categoryDetails);

        for (int i = 0; i < categoryDetails.size(); i++) {
            MainController.logger.debug(categoryDetails.get(i).toString());
        }	// end for

        return model;
    }	// end public ModelAndView allCategoryDetails() {}

    @Deprecated
    @RequestMapping(value = {"allCategoryDetailsNew"}, method = RequestMethod.GET)
    public ModelAndView allCategoryDetailsNew() {
        MainController.logger.debug(new Date() + " public ModelAndView allCategoryDetailsNew() {}");

        ModelAndView model = new ModelAndView();
        model.setViewName("allCategoryDetails");

        List<CategoryDetails> categoryDetails = ((CategoryDetailsManager) categoryDetailsManager).getAllCategoryDetails();
        model.addObject("categoryDetails", categoryDetails);

        return model;
    }   // end public ModelAndView allCategoryDetailsNew() {}

    @RequestMapping(value = {"allInventoryDetails"}, method = RequestMethod.GET)
    public ModelAndView allInventoryDetails() {
        MainController.logger.debug(new Date() + " public ModelAndView allInventoryDetails() {}");

        ModelAndView model = new ModelAndView();
        model.setViewName("allInventoryDetails");

        List<InventoryDetails> inventoryDetails = (List<InventoryDetails>) inventoryDetailsManager.getAllEntities();

        model.addObject("inventoryDetails", inventoryDetails);

        return model;
    }	// end public ModelAndView allInventoryDetails() {}

    @Deprecated
    @RequestMapping(value = {"allInventoryDetailsNew"}, method = RequestMethod.GET)
    public ModelAndView allInventoryDetailsNew() {
        MainController.logger.debug(new Date() + " public ModelAndView allInventoryDetailsNew() {}");

        ModelAndView model = new ModelAndView();
        model.setViewName("allInventoryDetails");

        List<InventoryDetails> inventoryDetails = ((InventoryDetailsManager) inventoryDetailsManager).getAllInventories();

        model.addObject("inventoryDetails", inventoryDetails);

        return model;
    }	// end public ModelAndView allInventoryDetails() {}

    @Deprecated
    @RequestMapping(value = {"allInventories"}, method = RequestMethod.GET)
    public ModelAndView allInventories() {
        MainController.logger.debug(new Date() + " public ModelAndView allInventories() {}");

        ModelAndView model = new ModelAndView();
        model.setViewName("allInventoryDetails");

        List<InventoryDetails> inventories = ((InventoryDetailsManager) inventoryDetailsManager).getAllInventories();
        model.addObject("inventoryDetails", inventories);

        return model;
    }   // end public ModelAndView allInventories() {}

    @RequestMapping(value = {"addItem"}, method = RequestMethod.GET)
    public ModelAndView addItem() {
        MainController.logger.debug(new Date() + " public ModelAndView addItem() {}");

        ModelAndView model = new ModelAndView();
        model.setViewName("addItem");

        Location locaionStub = new Location(-1, "---", "---");
        List<Location> locations = new ArrayList<Location>();
        locations.add(locaionStub);
        locations.addAll((List<Location>) this.locationManager.getAllEntities());

        Item itemStub = new Item(-1, "---", "---", -1, "---");
        List<Item> containers = new ArrayList<Item>();
        containers.add(itemStub);
//        containers.addAll((List<Item>) this.itemManager.getAllEntities());
        containers.addAll(itemManager.getAllItems());

        List<Category> categories = (List<Category>) this.categoryManager.getAllEntities();

        model.addObject("containers", containers);
        model.addObject("locations", locations);
        model.addObject("categories", categories);
        model.addObject("addItem", itemStub);

        return model;
    }   // end public ModelAndView addItem() {}

    @RequestMapping(value = {"addItem"}, method = RequestMethod.POST)
    public ModelAndView saveItem(@ModelAttribute("addItem") Item item, BindingResult result) {

        MainController.logger.debug(new Date() + " public ModelAndView addItem() {}");
//        MainController.logger.debug(String.valueOf(catDetails.getCategories().size()));

        this.itemValidator.validate(item, result);

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();

            Location locaionStub = new Location(-1, "---", "---");
            List<Location> locations = new ArrayList<Location>();
            locations.add(locaionStub);
            locations.addAll((List<Location>) this.locationManager.getAllEntities());

            Item itemStub = new Item(-1, "---", "---", -1, "---");
            List<Item> containers = new ArrayList<Item>();
            containers.add(itemStub);
//            containers.addAll((List<Item>) this.itemManager.getAllEntities());
            containers.addAll(itemManager.getAllItems());

            List<Category> categories = (List<Category>) this.categoryManager.getAllEntities();

            mav.addObject("containers", containers);
            mav.addObject("locations", locations);
            mav.addObject("categories", categories);

//            model.addObject("addItem", itemStub);
//        model.getModelMap().addAttribute("addItem", itemStub);
            return mav;
        }   // end if (errorMav path)        

        ModelAndView model = new ModelAndView();
        model.setViewName("redirect:allItems");

//        MainController.logger.debug(new Date() + " added entity with id " + this.itemManager.addNewEntity(item));
        MainController.logger.debug(new Date() + " added entity with id " + this.itemManager.addItem(item));

        return model;
    }   // end public ModelAndView addItem() {}

    @RequestMapping(value = {"addItemWithDetails"}, method = RequestMethod.GET)
    public ModelAndView addItemWithDetails() {
        MainController.logger.debug(new Date() + " public ModelAndView addItemWithDetails() {}");

        ModelAndView mav = new ModelAndView("addItemWithDetails");

        Item itemStub = new Item(-1, "---", "---", -1, "---");
        List<Category> categories = (List<Category>) this.categoryManager.getAllEntities();
        List<Item> containersStub = new ArrayList<Item>();
        List<Item> containers = inventoryDetailsManager.getAllContainers();
        containersStub.add(itemStub);
        containersStub.addAll(containers);
//        containers.addAll(((InventoryDetailsManager)inventoryDetailsManager).getAllContainers());              
//        containers.addAll(inventoryDetailsManager.getAllContainers());    //TODO: try to comprehend why this does not work
        Location locaionStub = new Location(-1, "---", "---");
        List<Location> locations = new ArrayList<Location>();
        locations.add(locaionStub);
        locations.addAll((List<Location>) this.locationManager.getAllEntities());

        SimpleItemAdder itemAdder = new SimpleItemAdder(itemStub, -1, new ArrayList<Integer>());

        mav.addObject("newItemWithDetails", itemAdder);
        mav.addObject("containers", containersStub);
        mav.addObject("locations", locations);
        mav.addObject("categories", categories);

        return mav;
    }

//  TODO: add transaction support    
    @RequestMapping(value = {"addItemWithDetails"}, method = RequestMethod.POST)
    public ModelAndView saveItemWithDetails(@ModelAttribute("newItemWithDetails") SimpleItemAdder newItemAdder, BindingResult result) {
        MainController.logger.debug(new Date() + " public ModelAndView addItemWithDetails() {}");

        this.itemAdderValidator.validate(newItemAdder, result);

        if (result.hasErrors()) {

            ModelAndView mav = new ModelAndView("addItemWithDetails");

            Item itemStub = new Item(-1, "---", "---", -1, "---");
            Item container = new Item();
            List<Category> categories = (List<Category>) categoryManager.getAllEntities();
            List<Item> containersStub = new ArrayList<Item>();
            List<Item> containers = inventoryDetailsManager.getAllContainers();
            containersStub.add(itemStub);
            containersStub.addAll(containers);

            Location locaionStub = new Location(-1, "---", "---");
            List<Location> locations = new ArrayList<Location>();
            locations.add(locaionStub);
            locations.addAll((List<Location>) locationManager.getAllEntities());

            mav.addAllObjects(result.getModel());
            mav.addObject("containers", containersStub);
            mav.addObject("locations", locations);
            mav.addObject("categories", categories);

            return mav;
        }

        Item newItem = newItemAdder.getItem();
        List<Integer> categories = newItemAdder.getCategories();
        int containerId = newItemAdder.getContainerId();

//        CategoryDetails cd = ((CategoryDetailsManager) categoryDetailsManager).getNewEntity(newItemAdder);
        CategoryDetails cd = categoryDetailsManager.getNewEntityByDetails(newItemAdder);
//        InventoryDetails id = ((InventoryDetailsManager) inventoryDetailsManager).getNewEntity(newItemAdder);
        InventoryDetails id = inventoryDetailsManager.getNewEntityByDetails(newItemAdder);

//        int itemId = this.itemManager.addNewEntity(newItem);
        int itemId = this.itemManager.addItem(newItem);
        newItem.setId(itemId);
        logger.debug(itemId + "\n"
                + +this.categoryDetailsManager.addNewEntity(cd) + "\n"
                + +this.inventoryDetailsManager.addNewEntity(id) + "\n");

        return new ModelAndView("redirect:allCategoryDetails");
    }

    @RequestMapping(value = {"addLocation"}, method = RequestMethod.GET)
    public ModelAndView addLocation() {
        ModelAndView mav = new ModelAndView("addLocation");

        Location locationStub = new Location(-1, "---", "---");
        mav.addObject("addLocation", locationStub);
        return mav;
    }   // end public ModelAndView addLocation() {}

    @RequestMapping(value = {"addLocation"}, method = RequestMethod.POST)
    public ModelAndView saveLocation(@ModelAttribute("addLocation") Location location, BindingResult result) {
        ModelAndView mav = new ModelAndView("redirect:allLocations");

        this.locationValidator.validate(location, result);

        if (result.hasErrors()) {
            ModelAndView errorMav = new ModelAndView();
//            Location locationStub = new Location(-1, "---", "---");
//            errorMav.addObject("addLocation", locationStub);
            errorMav.addAllObjects(result.getModel());  // TODO: figure out why explicit adding is needed here
            logger.debug(result.getErrorCount());
            return errorMav;
        }   // end if

        MainController.logger.debug(new Date() + " added " + this.locationManager.addNewEntity(location) + " entity[es]");
        return mav;
    }   // end public ModelAndView saveLocation(@ModelAttribute("addLocation") Location location, BindingResult result) {}

    @RequestMapping(value = {"addCategory"}, method = RequestMethod.GET)
    public ModelAndView addCategory() {
        ModelAndView mav = new ModelAndView("addCategory");
        Category categoryStub = new Category(-1, "---", "---");
        mav.addObject("addCategory", categoryStub);

        return mav;
    }   // end public ModelAndView addCategory() {}

    @RequestMapping(value = {"addCategory"}, method = RequestMethod.POST)
    public ModelAndView saveCategory(@ModelAttribute("addCategory") Category category, BindingResult result) {

        this.categoryValidator.validate(category, result);
        if (result.hasErrors()) {
            ModelAndView errorMav = new ModelAndView();
            errorMav.addAllObjects(result.getModel());

            return errorMav;
        }   // end if

        logger.debug(new Date() + " added: " + this.categoryManager.addNewEntity(category));

        ModelAndView mav = new ModelAndView("redirect:allCategories");
        return mav;
    }   // end public ModelAndView saveCategory(@ModelAttribute("addCategory") Category category, BindingResult result) {}

    @RequestMapping(value = {"getItemDetails"}, method = RequestMethod.GET)
    public ModelAndView getItemDetails(@RequestParam("itemId") int itemId) {
        ModelAndView mav = new ModelAndView("itemDetails");

//        Item item = itemManager.getEntityById(itemId);
        Item item = itemManager.getItemById(itemId);
//        CategoryDetails cd = ((CategoryDetailsManager) categoryDetailsManager).getCategoryDetailsByItem(item);
        CategoryDetails cd = (CategoryDetails) categoryDetailsManager.getEntityById(itemId);
//        InventoryDetails id = ((InventoryDetailsManager) inventoryDetailsManager).getInventoryDetailsByContainer(item);
        InventoryDetails id = (InventoryDetails) inventoryDetailsManager.getEntityById(itemId);
//        Location location = ((LocationManager) locationManager).getEntityById(item.getLocationId());
        Location location = (Location) locationManager.getEntityById(item.getLocationId());
        item.setLocation(location.getName());

        mav.addObject("itemWithLocation", item);
        mav.addObject("categoryDetails", cd);
        mav.addObject("inventoryDetails", id);
        return mav;
    }

    @RequestMapping(value = {"editItemDetails"}, method = RequestMethod.GET)
    public ModelAndView editItemDetails(@RequestParam("itemId") int itemId) {
        ModelAndView mav = new ModelAndView("editItemDetails");

//        Item item = itemManager.getEntityById(itemId);
        Item item = itemManager.getItemById(itemId);
//        Item container = ((InventoryDetailsManager) inventoryDetailsManager).getContainerByItem(item);
//        CategoryDetails cd = ((CategoryDetailsManager) categoryDetailsManager).getCategoryDetailsByItem(item);
        CategoryDetails cd = (CategoryDetails) categoryDetailsManager.getEntityById(itemId);
//        InventoryDetails id = ((InventoryDetailsManager) inventoryDetailsManager).getInventoryDetailsByContainer(item);
        InventoryDetails id = (InventoryDetails) inventoryDetailsManager.getEntityById(itemId);
//        Location location = (Location) locationManager.getEntityById(item.getLocationId());
        Location location = (Location) locationManager.getEntityById(item.getLocationId());
        Item container = inventoryDetailsManager.getContainerByItemId(itemId);
        item.setLocation(location.getName());

        List<Integer> categoryIds = new ArrayList<Integer>();
        if (cd.getCategories() != null && !cd.getCategories().isEmpty()) {
            for (Category category : cd.getCategories()) {
                categoryIds.add(category.getId());
            }
        }
        SimpleItemAdder itemAdder = new SimpleItemAdder(item, ((container == null) ? -1 : container.getId()), categoryIds);

        mav.addObject("editItemDetails", itemAdder);
        mav.addObject("categoryDetails", cd);
        mav.addObject("inventoryDetails", id);
        mav.addObject("locations", locationManager.getAllEntities());
        mav.addObject("categoriesList", categoryManager.getAllEntities());
//        mav.addObject("containers", this.itemManager.getAllEntities()); //TODO add filtration by 'has container category' query
//        mav.addObject("containers", ((InventoryDetailsManager) inventoryDetailsManager).getAllContainers());
        mav.addObject("containers", inventoryDetailsManager.getAllContainers());

        return mav;
    }

//  TODO: add transaction support
    @RequestMapping(value = {"editItemDetails"}, method = RequestMethod.POST)
    public ModelAndView saveEditedItemDetails(
            @ModelAttribute("editItemDetails") SimpleItemAdder editedItemAdder,
            @RequestParam("itemId") int itemId,
            BindingResult result) {
        MainController.logger.debug(new Date() + " public ModelAndView saveEditedItemDetails() {}");

        this.itemAdderValidator.validate(editedItemAdder, result);

        if (result.hasErrors()) {

            ModelAndView mav = new ModelAndView("editItemDetails");

//            Item item = itemManager.getEntityById(itemId);
            Item item = itemManager.getItemById(itemId);
//            CategoryDetails cd = ((CategoryDetailsManager) categoryDetailsManager).getCategoryDetailsByItem(item);
            CategoryDetails cd = (CategoryDetails) categoryDetailsManager.getEntityById(itemId);
//          InventoryDetails id = ((InventoryDetailsManager) inventoryDetailsManager).getInventoryDetailsByContainer(item);
            InventoryDetails id = (InventoryDetails) inventoryDetailsManager.getEntityById(itemId);
//          Location location = (Location) locationManager.getEntityById(item.getLocationId());            
            Item container = id.getContainer();
            Location location = (Location) locationManager.getEntityById(item.getLocationId());
            item.setLocation(location.getName());

            List<Integer> categoryIds = new ArrayList<Integer>();
            for (Category category : cd.getCategories()) {
                categoryIds.add(category.getId());
            }

            SimpleItemAdder itemAdder = new SimpleItemAdder(item, ((container == null) ? -1 : container.getId()), categoryIds);

            mav.addAllObjects(result.getModel());
            //            mav.addObject("editItemDetails", itemAdder);
            mav.addObject("categoryDetails", cd);
            mav.addObject("inventoryDetails", id);
            mav.addObject("locations", locationManager.getAllEntities());
            mav.addObject("categoriesList", categoryManager.getAllEntities());
//            mav.addObject("containers", this.itemManager.getAllEntities()); //TODO add filtration by 'has container category' query
            mav.addObject("containers", inventoryDetailsManager.getAllContainers());

            return mav;
        }

        Item editedItem = editedItemAdder.getItem();
        List<Integer> categories = editedItemAdder.getCategories();
        int containerId = editedItemAdder.getContainerId();

        System.out.println(editedItemAdder.getCategories() == null ? " no categories info provided" : "size  " + editedItemAdder.getCategories().size());
        System.out.println(editedItemAdder.getContainerId() == -1 ? " no container info provided" : "desc " + editedItemAdder.getContainerId());

//        CategoryDetails cd = ((CategoryDetailsManager) categoryDetailsManager).getNewEntity(editedItemAdder);
        CategoryDetails cd = (CategoryDetails) categoryDetailsManager.getNewEntityByDetails(editedItemAdder);
//        InventoryDetails id = ((InventoryDetailsManager) inventoryDetailsManager).getNewEntity(editedItemAdder);
        InventoryDetails id = (InventoryDetails) inventoryDetailsManager.getNewEntityByDetails(editedItemAdder);

//        Item oldItem = this.itemManager.getEntityById(itemId);
        Item oldItem = this.itemManager.getItemById(itemId);
        if (!oldItem.equals(editedItem)) {

//            System.out.println(this.itemManager.updateEntity(editedItem));
            System.out.println(this.itemManager.updateItem(editedItem));

        }

        int oldContainerId = -1;
//        if (((InventoryDetailsManager) inventoryDetailsManager).getContainerByItem(oldItem) != null) {
        Item container = inventoryDetailsManager.getContainerByItemId(itemId);
        if (container != null) {
            oldContainerId = container.getId();
        }

        if (oldContainerId != editedItemAdder.getContainerId()) {
//            ((InventoryDetailsManager) inventoryDetailsManager).updateContainerForItem(oldContainerId, editedItemAdder.getContainerId(), editedItemAdder.getItem().getId());
            inventoryDetailsManager.updateEntity(id);
        }

        categoryDetailsManager.updateEntity(cd);

        return new ModelAndView("redirect:allCategoryDetails");
    }

    @RequestMapping(value = {"deleteItem"}, method = RequestMethod.GET)
    public ModelAndView deleteItem(@RequestParam("itemId") int itemId) {
        ModelAndView mav = new ModelAndView("redirect:allCategoryDetails");

        if (itemId > 0) {
//            Item item = this.itemManager.getEntityById(itemId);
            Item item = this.itemManager.getItemById(itemId);
            CategoryDetails cd = this.categoryDetailsManager.getEntityById(itemId);
            InventoryDetails id = this.inventoryDetailsManager.getEntityById(itemId);

//            ((InventoryDetailsManager) inventoryDetailsManager).deleteInventoryDetailsForItem(item);
            inventoryDetailsManager.deleteEntityById(itemId);
//            ((CategoryDetailsManager) categoryDetailsManager).deleteCategoriesForItem(cd);
            categoryDetailsManager.deleteEntityById(itemId);
//            ((ItemManager) itemManager).deleteItem(item);
//            itemManager.deleteEntityById(itemId);
            itemManager.deleteItemById(itemId);

        }

        return mav;
    }

    @RequestMapping(value = {"editLocation"}, method = RequestMethod.GET)
    public ModelAndView editLocation(@RequestParam("locationId") int locationId) {
        ModelAndView mav = new ModelAndView("editLocation");

        mav.addObject("editLocation", this.locationManager.getEntityById(locationId));

        return mav;
    }

    @RequestMapping(value = {"editLocation"}, method = RequestMethod.POST)
    public ModelAndView saveEditedLocation(@ModelAttribute("editLocation") Location editedLocation, BindingResult result) {

        this.locationValidator.validate(editedLocation, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("editLocation");
            mav.addAllObjects(result.getModel());
            return mav;
        }

        ModelAndView mav = new ModelAndView("redirect:allLocations");

        System.out.println(this.locationManager.updateEntity(editedLocation));

        return mav;
    }

    @RequestMapping(value = {"deleteLocation"}, method = RequestMethod.GET)
    public ModelAndView deleteLocation(@RequestParam("locationId") int locationId) {
        ModelAndView mav = new ModelAndView("redirect:allLocations");

        locationManager.deleteEntityById(locationId);

        return mav;
    }

    @RequestMapping(value = {"editCategory"}, method = RequestMethod.GET)
    public ModelAndView editCategory(@RequestParam("categoryId") int categoryId) {
        ModelAndView mav = new ModelAndView("editCategory");

        mav.addObject("editCategory", this.categoryManager.getEntityById(categoryId));

        return mav;
    }

    @RequestMapping(value = {"editCategory"}, method = RequestMethod.POST)
    public ModelAndView saveEditedLocation(@ModelAttribute("editCategory") Category editedCategory, BindingResult result) {

        this.categoryValidator.validate(editedCategory, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView("editLocation");
            mav.addAllObjects(result.getModel());
            return mav;
        }

        ModelAndView mav = new ModelAndView("redirect:allCategories");

        System.out.println(this.categoryManager.updateEntity(editedCategory));

        return mav;
    }

    @RequestMapping(value = {"deleteCategory"}, method = RequestMethod.GET)
    public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {
        ModelAndView mav = new ModelAndView("redirect:allCategories");

        if (categoryId > 0) {
            System.out.println("deleted " + categoryManager.deleteEntityById(categoryId));
        }

        return mav;
    }
}   // end public class MainController {}
