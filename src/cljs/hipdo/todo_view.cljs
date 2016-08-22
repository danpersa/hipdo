(ns hipdo.todo-view
  (:require
    [cljsjs.material-ui]
    [cljs-react-material-ui.core :as ui]
    [cljs-react-material-ui.reagent :as rui]
    [cljs-react-material-ui.icons :as ic]
    [re-frame.core :as re-frame]))

(defn icon-button-element []
  (ui/icon-button {:touch            true
                   :tooltip          "more"
                   :tooltip-position "bottom-left"}
                  (ic/navigation-more-vert)))

(defn right-icon-menu []
  (ui/icon-menu
    {:icon-button-element (icon-button-element)}
    (ui/menu-item "Hello")))

(defn render-todo-list-item [todo]
  (ui/list-item {:primary-text      (todo :title)
                 :left-icon         (ui/checkbox)
                 :right-icon-button (right-icon-menu)
                 :on-click          #(println "clicked")}))

(defn todos-list [todos]
  (ui/list
    (ui/text-field {:hint-text "Create task" :full-width true})

    (map render-todo-list-item todos)

    (ui/list-item {:primary-text      "Create a new app"
                   :left-icon         (ui/checkbox)
                   :right-icon-button (right-icon-menu)
                   :on-click          #(println "clicked")})
    (ui/divider)
    (ui/list-item {:primary-text      (ui/text-field {:full-width true :default-value "Hello"})
                   :secondary-text    "xxx"
                   :left-icon         (ui/checkbox)
                   :right-icon-button (right-icon-menu)
                   :on-click          #(println "clicked")})
    (ui/divider)
    (ui/list-item {:primary-text      "Do some other stull"
                   :left-checkbox     (ui/checkbox)
                   :right-icon-button (right-icon-menu)})))





(defn left-menu-content []
  (ui/list
    (ui/list-item {:primary-text "Inbox"
                   :left-icon    (ic/content-inbox)})

    (ui/list-item {:primary-text "Starred"
                   :left-icon    (ic/action-grade)})

    (ui/list-item {:primary-text "Drafts"
                   :left-icon    (ic/content-drafts)})

    (ui/subheader {:inset true} "Projects")
    (ui/list-item {:left-icon    (ic/file-folder)
                   :primary-text "Some Project"
                   :right-icon   (ic/action-info)})))

(defn todo-page [left-menu-visible? todos]
  [rui/mui-theme-provider
   {:mui-theme (ui/get-mui-theme
                 {:palette {:text-color (ui/color :grey700)}})}
   [:div {:class "row"}
    [:div {:class "visible-xs"}
     (ui/drawer
       {:docked            false
        :open              left-menu-visible?
        :on-request-change #(re-frame/dispatch [:hide-left-menu])}

       (left-menu-content))]


    [:div {:class-name "col-lg-3 col-md-3 col-sm-4 hidden-xs"}
     [rui/paper
      [rui/app-bar {:title                 "Menu"
                    :show-menu-icon-button false}]

      (left-menu-content)]]


    [:div {:class "col-lg-9 col-md-9 col-sm-8 col-xs-12"}
     [rui/app-bar {:title              "Title"
                   :icon-element-left  (ui/icon-button {:class-name   "visible-xs"
                                                        :on-touch-tap #(re-frame/dispatch [:show-left-menu])}
                                                       (ic/navigation-menu))
                   :on-left-icon-button-touch-tap
                                       #(re-frame/dispatch [:show-left-menu])
                   :icon-element-right (ui/icon-button
                                         (ic/action-account-balance-wallet))}]
     [rui/paper {:class-name "pad-20"}
      [rui/mui-theme-provider
       {:mui-theme (ui/get-mui-theme {:palette {:text-color (ui/color :grey700)}})}

       (todos-list todos)]]]]])
