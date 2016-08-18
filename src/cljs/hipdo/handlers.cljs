(ns hipdo.handlers
    (:require [re-frame.core :as re-frame]
              [hipdo.db :as db]))

(re-frame/register-handler
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/register-handler
 :set-active-panel
 (fn [db [_ active-panel]]
   (assoc db :active-panel active-panel)))

(re-frame/register-handler
  :show-left-menu
  (fn [db [_ _]]
    (assoc db :left-menu-visible? true)))

(re-frame/register-handler
  :hide-left-menu
  (fn [db [_ _]]
    (assoc db :left-menu-visible? false)))
