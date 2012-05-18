package srvrepo.gui

import srvrepo.*

class QualityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [qualityInstanceList: Quality.list(params), qualityInstanceTotal: Quality.count()]
    }

    def create = {
		if(params.service_id) params.service = Service.get(params.service_id.toLong())
        def qualityInstance = new Quality()
        qualityInstance.properties = params
        return [qualityInstance: qualityInstance]
    }

    def save = {
		if(params.service && !(params.service instanceof Service)) params.service = Service.get(params.service)
		if(params.keyword && !(params.keyword instanceof Keyword)) params.keyword = Keyword.get(params.keyword)
        def qualityInstance = new Quality(params)
		qualityInstance.contentType = params.file.contentType
		qualityInstance.fileName = params.file.originalFilename
        if (qualityInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'quality.label', default: 'Quality'), qualityInstance.id])}"
            redirect(action: "show", id: qualityInstance.id)
        }
        else {
			qualityInstance.errors.each {
				System.out.println(it)
			}
            render(view: "create", model: [qualityInstance: qualityInstance])
        }
    }

    def show = {
        def qualityInstance = Quality.get(params.id)
        if (!qualityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'quality.label', default: 'Quality'), params.id])}"
            redirect(action: "list")
        }
        else {
            [qualityInstance: qualityInstance]
        }
    }

	def download = {
		def qualityInstance = Quality.get(params.id)
		response.setContentType( qualityInstance?.contentType )
		response.setHeader("Content-Disposition", "attachment; filename=${qualityInstance?.fileName}")
		response.getOutputStream() << new ByteArrayInputStream( qualityInstance?.file )
	}

    def edit = {
        def qualityInstance = Quality.get(params.id)
        if (!qualityInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'quality.label', default: 'Quality'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [qualityInstance: qualityInstance]
        }
    }

    def update = {
		if(params.keyword && !(params.keyword instanceof Keyword)) params.keyword = Keyword.get(params.keyword)
        def qualityInstance = Quality.get(params.id)
        if (qualityInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (qualityInstance.version > version) {
                    
                    qualityInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'quality.label', default: 'Quality')] as Object[], "Another user has updated this Quality while you were editing")
                    render(view: "edit", model: [qualityInstance: qualityInstance])
                    return
                }
            }
			if(params.service && !(params.service instanceof Service)) params.service = Service.get(params.service)
            qualityInstance.properties = params
            if (!qualityInstance.hasErrors() && qualityInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'quality.label', default: 'Quality'), qualityInstance.id])}"
                redirect(action: "show", id: qualityInstance.id)
            }
            else {
                render(view: "edit", model: [qualityInstance: qualityInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'quality.label', default: 'Quality'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def qualityInstance = Quality.get(params.id)
        if (qualityInstance) {
            try {
                qualityInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'quality.label', default: 'Quality'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'quality.label', default: 'Quality'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'quality.label', default: 'Quality'), params.id])}"
            redirect(action: "list")
        }
    }
}
