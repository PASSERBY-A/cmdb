/*
 * File: app/../store/ReportMenus.js
 *
 * This file was generated by Sencha Architect version 2.1.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Ext JS 4.0.x library, under independent license.
 * License of Sencha Architect does not include license for Ext JS 4.0.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('main.store.ReportMenus', {
    extend: 'Ext.data.TreeStore',

    requires: [
        'main.model.Menu'
    ],

    constructor: function(cfg) {
        var me = this;
        cfg = cfg || {};
        me.callParent([Ext.apply({
            autoLoad: true,
            storeId: 'MyJsonTreeStore2',
            model: 'main.model.Menu',
            nodeParam: 'id',
            root: {
                expanded: true,
                text: 'HP',
                id: 'root'
            },
            proxy: {
                type: 'ajax',
                url: '../main/menuTree?type=report',
                reader: {
                    type: 'json'
                }
            }
        }, cfg)]);
    }
});