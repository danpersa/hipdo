(ns hipdo.views
  (:require [re-frame.core :as re-frame]
            [hipdo.todo-view :as todo-view]))


;; home

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [:div [:a {:href "#/about"} "go to About Page"]
             [:a {:href "#/todo"} "go to Todo Page"]]])))


;; about

(defn about-panel []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:href "#/"} "go to Home Page"]
           [:a {:href "#/todo"} "go to Todo Page"]]]))

(defn todo-panel []
  (let [left-menu-visible? (re-frame/subscribe [:left-menu-visible?])
        todos (re-frame/subscribe [:todos])]
    (fn []
     (todo-view/todo-page @left-menu-visible? @todos))))

;; main

(defmulti panels identity)
(defmethod panels :home-panel [] [home-panel])
(defmethod panels :about-panel [] [about-panel])
(defmethod panels :todo-panel [] [todo-panel])
(defmethod panels :default [] [:div])

(defn show-panel
  [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
