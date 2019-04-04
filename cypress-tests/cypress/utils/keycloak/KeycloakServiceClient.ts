/*********************************************************************
 * Copyright (c) 2018 Red Hat, Inc.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 **********************************************************************/


 export class KeycloakServiceClient{

    private static bearerToken: string = null;
    private static refreshToken: string = null;


    public static doAuthorizationRequest(){
        cy.request({
            method: 'POST',
            url: 'http://keycloak-eclipse-che.172.19.20.205.nip.io/auth/realms/che/protocol/openid-connect/token',
            form: true,
            body: {
                client_id: 'che-public',
                username: 'admin',
                password: 'admin',
                grant_type: 'password'
            }
        }).then(responce => {
            this.bearerToken = responce.body.access_token;
            this.refreshToken = responce.body.refresh_token;

            cy.request({
                method: 'GET',
                url: 'http://che-eclipse-che.172.19.20.205.nip.io/api/workspace/namespace/admin',
                headers: {
                    'Authorization': `Bearer ${this.bearerToken}`
                    
                }

            });
        })
    }

    




    // public getAccessToken(): string{

    // }
    
    // public getRefreshToken: string {

    // }



 }