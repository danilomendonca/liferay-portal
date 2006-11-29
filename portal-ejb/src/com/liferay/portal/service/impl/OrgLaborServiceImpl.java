/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.service.impl;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.model.Organization;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.OrgLaborLocalServiceUtil;
import com.liferay.portal.service.OrgLaborService;
import com.liferay.portal.service.permission.LocationPermission;
import com.liferay.portal.service.permission.OrganizationPermission;
import com.liferay.portal.service.persistence.OrgLaborUtil;
import com.liferay.portal.service.persistence.OrganizationUtil;

import java.util.List;

/**
 * <a href="OrgLaborServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class OrgLaborServiceImpl
	extends PrincipalBean implements OrgLaborService {

	public OrgLabor addOrgLabor(
			String organizationId, String typeId, int sunOpen, int sunClose,
			int monOpen, int monClose, int tueOpen, int tueClose, int wedOpen,
			int wedClose, int thuOpen, int thuClose, int friOpen, int friClose,
			int satOpen, int satClose)
		throws PortalException, SystemException {

		checkPermission(organizationId, ActionKeys.UPDATE);

		return OrgLaborLocalServiceUtil.addOrgLabor(
			organizationId, typeId, sunOpen, sunClose, monOpen, monClose,
			tueOpen, tueClose, wedOpen, wedClose, thuOpen, thuClose, friOpen,
			friClose, satOpen, satClose);
	}

	public void deleteOrgLabor(String orgLaborId)
		throws PortalException, SystemException {

		OrgLabor orgLabor = OrgLaborUtil.findByPrimaryKey(orgLaborId);

		checkPermission(orgLabor.getOrganizationId(), ActionKeys.UPDATE);

		OrgLaborLocalServiceUtil.deleteOrgLabor(orgLaborId);
	}

	public OrgLabor getOrgLabor(String orgLaborId)
		throws PortalException, SystemException {

		OrgLabor orgLabor = OrgLaborUtil.findByPrimaryKey(orgLaborId);

		checkPermission(orgLabor.getOrganizationId(), ActionKeys.VIEW);

		return orgLabor;
	}

	public List getOrgLabors(String organizationId)
		throws PortalException, SystemException {

		checkPermission(organizationId, ActionKeys.VIEW);

		return OrgLaborLocalServiceUtil.getOrgLabors(organizationId);
	}

	public OrgLabor updateOrgLabor(
			String orgLaborId, int sunOpen, int sunClose, int monOpen,
			int monClose, int tueOpen, int tueClose, int wedOpen, int wedClose,
			int thuOpen, int thuClose, int friOpen, int friClose, int satOpen,
			int satClose)
		throws PortalException, SystemException {

		OrgLabor orgLabor = OrgLaborUtil.findByPrimaryKey(orgLaborId);

		checkPermission(orgLabor.getOrganizationId(), ActionKeys.UPDATE);

		return OrgLaborLocalServiceUtil.updateOrgLabor(
			orgLaborId, sunOpen, sunClose, monOpen, monClose, tueOpen, tueClose,
			wedOpen, wedClose, thuOpen, thuClose, friOpen, friClose, satOpen,
			satClose);
	}

	protected void checkPermission(String organizationId, String actionId)
		throws PortalException, SystemException {

		Organization organization =
			OrganizationUtil.findByPrimaryKey(organizationId);

		if (organization.isRoot()) {
			OrganizationPermission.check(
				getPermissionChecker(), organizationId, actionId);
		}
		else {
			LocationPermission.check(
				getPermissionChecker(), organizationId, actionId);
		}
	}

}