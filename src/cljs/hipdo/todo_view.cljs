(ns hipdo.todo-view
  (:require
    [cljsjs.material-ui]
    [cljs-react-material-ui.core :as ui]
    [cljs-react-material-ui.reagent :as rui]
    [cljs-react-material-ui.icons :as ic]
    [re-frame.core :as re-frame]))

(defn render-todo [todo]
  (ui/table-row
    (ui/table-row-column (todo :title))))

(defn todos-table [todos]
  (ui/table
    {:height "250px"}
    (ui/table-header
      {:display-select-all  false
       :adjust-for-checkbox true}
      (ui/table-row
        (ui/table-header-column "Name")))

    (ui/table-body
      (map render-todo todos))))

(defn todo-page [left-menu-visible? todos]
  [rui/mui-theme-provider
   {:mui-theme (ui/get-mui-theme
                 {:palette {:text-color (ui/color :green600)}})}
   [:div
    [rui/app-bar {:title              "Title"
                  :on-left-icon-button-touch-tap
                                      #(re-frame/dispatch [:show-left-menu])
                  :icon-element-right (ui/icon-button
                                        (ic/action-account-balance-wallet))}]
    (ui/drawer
      {:docked            false
       :open              left-menu-visible?
       :on-request-change #(re-frame/dispatch [:hide-left-menu])}
      (ui/menu-item {:on-touch-tap #(println "Menu Item Clicked")}
                    "Menu Item")
      (ui/menu-item "Menu Item 2"))

    [rui/paper
     [:div "Hello"]
     [rui/mui-theme-provider
      {:mui-theme (ui/get-mui-theme {:palette {:text-color (ui/color :blue200)}})}
      [rui/raised-button {:label "Blue button"}]]
     (ic/action-home {:color (ui/color :grey600)})
     [rui/raised-button {:label        "Click me"
                         :icon         (ic/social-group)
                         :on-touch-tap #(println "clicked")}]
     (todos-table todos)]]])
