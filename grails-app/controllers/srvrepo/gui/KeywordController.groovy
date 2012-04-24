package srvrepo.gui

import srvrepo.*

class KeywordController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [keywordInstanceList: Keyword.list(params), keywordInstanceTotal: Keyword.count()]
    }

    def create = {
        def keywordInstance = new Keyword()
        keywordInstance.properties = params
        return [keywordInstance: keywordInstance]
    }

    def save = {
        def keywordInstance = new Keyword(params)
        if (keywordInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'keyword.label', default: 'Keyword'), keywordInstance.id])}"
            redirect(action: "show", id: keywordInstance.id)
        }
        else {
            render(view: "create", model: [keywordInstance: keywordInstance])
        }
    }

    def show = {
        def keywordInstance = Keyword.get(params.id)
        if (!keywordInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'keyword.label', default: 'Keyword'), params.id])}"
            redirect(action: "list")
        }
        else {
            [keywordInstance: keywordInstance]
        }
    }

    def edit = {
        def keywordInstance = Keyword.get(params.id)
        if (!keywordInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'keyword.label', default: 'Keyword'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [keywordInstance: keywordInstance]
        }
    }

    def update = {
        def keywordInstance = Keyword.get(params.id)
        if (keywordInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (keywordInstance.version > version) {
                    
                    keywordInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'keyword.label', default: 'Keyword')] as Object[], "Another user has updated this Keyword while you were editing")
                    render(view: "edit", model: [keywordInstance: keywordInstance])
                    return
                }
            }
            keywordInstance.properties = params
            if (!keywordInstance.hasErrors() && keywordInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'keyword.label', default: 'Keyword'), keywordInstance.id])}"
                redirect(action: "show", id: keywordInstance.id)
            }
            else {
                render(view: "edit", model: [keywordInstance: keywordInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'keyword.label', default: 'Keyword'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def keywordInstance = Keyword.get(params.id)
        if (keywordInstance) {
            try {
                keywordInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'keyword.label', default: 'Keyword'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'keyword.label', default: 'Keyword'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'keyword.label', default: 'Keyword'), params.id])}"
            redirect(action: "list")
        }
    }
}
